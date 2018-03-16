package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.TestApplication;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.main.espresso.MatcherUtils;
import com.rafagarcia.countries.main.espresso.RecyclerViewItemCountAssertion;
import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.utils.FileUtils;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import io.appflate.restmock.RESTMockServer;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.appflate.restmock.utils.RequestMatchers.pathEndsWith;
import static org.hamcrest.Matchers.not;

/**
 * Created by Rafa on 15/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class CountryListActivityTest {

    private CountriesLocalDataSource countriesLocalDataSource;
    private CountriesMemoryDataSource countriesMemoryDataSource;

    @Rule
    public IntentsTestRule<CountryListActivity> mActivityTestRule = new IntentsTestRule<>(CountryListActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        RESTMockServer.reset();
        CountriesProvider countriesProvider = ((TestApplication) getInstrumentation().getTargetContext().getApplicationContext()).getApplicationComponent().getCountriesProvider();
        countriesLocalDataSource = countriesProvider.getLocalDataSource();
        countriesMemoryDataSource = countriesProvider.getMemoryDataSource();
        countriesLocalDataSource.clear();
        countriesMemoryDataSource.clear();
    }

    @Test
    public void it_shows_screen_when_launched() {
        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.list_of_countries_root)).check(matches(isDisplayed()));
    }

    @Test
    public void it_shows_2_countries_coming_from_remote_when_launched_and_there_is_no_data_in_memory_nor_local_data_sources() {
        RESTMockServer.whenGET(pathEndsWith("all")).thenReturnFile(200, "json/list_of_countries_1.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(2));
    }

    @Test
    public void it_shows_12_countries_coming_from_local_when_launched_and_there_is_data_in_local_data_source() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_2.json");
        countriesLocalDataSource.save(countries);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(12));
    }

    @Test
    public void it_shows_6_countries_coming_from_memory_when_launched_and_there_is_data_in_memory_data_source() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_3.json");
        countriesMemoryDataSource.save(countries);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(6));
    }

    @Test
    public void given_search_input_spain_when_filtering_countries_then_display_only_spain() {
        RESTMockServer.whenGET(pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.searchTextView)).perform(typeText("Spain"));
        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(1));
        ViewInteraction countryName = onView(MatcherUtils.withIndex(withId(R.id.nameTextView), 0));
        countryName.check(matches(withText("Spain")));
    }

    @Test
    public void given_search_input_ge_when_filtering_countries_then_display_germany_and_georgia() {
        RESTMockServer.whenGET(pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.searchTextView)).perform(typeText("ge"));
        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(2));
        ViewInteraction firstCountryName = onView(MatcherUtils.withIndex(withId(R.id.nameTextView), 0));
        firstCountryName.check(matches(withText("Georgia")));
        ViewInteraction secondCountryName = onView(MatcherUtils.withIndex(withId(R.id.nameTextView), 1));
        secondCountryName.check(matches(withText("Germany")));
    }

    @Test
    public void given_search_input_unreadable_when_filtering_countries_then_display_no_countries() {
        RESTMockServer.whenGET(pathEndsWith("all")).thenReturnFile(200, "json/all_countries.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.action_search)).perform(click());
        onView(withId(R.id.searchTextView)).perform(typeText("dsgdskgs"));
        onView(withId(R.id.countries_list_recycler_view)).check(new RecyclerViewItemCountAssertion(0));
    }

    @Test
    public void when_user_scrolls_down_then_go_to_top_floating_button_is_visible() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_2.json");
        countriesLocalDataSource.save(countries);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).perform(scrollToPosition(10));

        onView(withId(R.id.go_to_top_button)).check(matches(isDisplayed()));
    }

    @Test
    public void when_user_scrolls_down_and_then_to_top_then_go_to_top_floating_button_is_not_visible() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_2.json");
        countriesLocalDataSource.save(countries);
        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).perform(scrollToPosition(10));
        onView(withId(R.id.countries_list_recycler_view)).perform(scrollToPosition(0));
        onView(withId(R.id.go_to_top_button)).check(matches(not(isDisplayed())));
    }

    @Test
    public void when_user_scrolls_down_and_then_click_on_go_to_top_button_then_list_scrolls_to_top() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_2.json");
        countriesLocalDataSource.save(countries);
        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countries_list_recycler_view)).perform(scrollToPosition(10));
        onView(withId(R.id.go_to_top_button)).perform(click());
        ViewInteraction firstCountryName = onView(MatcherUtils.withIndex(withId(R.id.country_card_root), 0));
        firstCountryName.check(matches(isDisplayed()));
    }

    private List<Country> getCountriesFromJson(String path) throws IOException {
        String json = FileUtils.getJsonFromAsset(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Country>>() {});
    }
}