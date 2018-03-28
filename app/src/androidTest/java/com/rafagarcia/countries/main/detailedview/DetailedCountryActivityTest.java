package com.rafagarcia.countries.main.detailedview;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.TestApplication;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.main.espresso.MatcherUtils;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Rafa on 28/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class DetailedCountryActivityTest {

    @Rule
    public IntentsTestRule<DetailedCountryActivity> mActivityTestRule = new IntentsTestRule<>(DetailedCountryActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        RESTMockServer.reset();
        List<Country> countries = getCountriesFromJson("json/all_countries.json");
        CountriesProvider countriesProvider = ((TestApplication) getInstrumentation().getTargetContext().getApplicationContext()).getApplicationComponent().getCountriesProvider();
        countriesProvider.getMemoryDataSource().save(countries);
    }

    @Test
    public void it_shows_screen_when_launched() {
        Intent intent = new Intent();
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain");

        mActivityTestRule.launchActivity(intent);

        onView(withId(R.id.detailed_view_root)).check(matches(isDisplayed()));
    }

    @Test
    public void given_country_then_it_shows_correct_data_() {
        Intent intent = new Intent();
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain");

        mActivityTestRule.launchActivity(intent);

        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Spain")));
        onView(withId(R.id.capital_text_view)).check(matches(withText("Madrid")));
        onView(withId(R.id.continent_text_view)).check(matches(withText("Europe")));
        onView(withId(R.id.region_text_view)).check(matches(withText("Southern Europe")));
        onView(withId(R.id.population_text_view)).check(matches(withText("Population: 46.4M")));
        onView(withId(R.id.area_text_view)).check(matches(withText("Area: 505.9 km²")));
        onView(withId(R.id.demonym_text_view)).check(matches(withText("Demonym: Spanish")));
        onView(withId(R.id.native_name_text_view)).check(matches(withText("Native name: España")));
    }

    @Test
    public void given_country_with_no_borders_then_it_shows_no_border_countries() throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Martinique");

        mActivityTestRule.launchActivity(intent);

        onView(withId(R.id.borders_linear_layout)).check(matches(hasChildCount(0)));
    }

    @Test
    public void given_country_with_borders_then_it_shows_border_countries() throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain");

        mActivityTestRule.launchActivity(intent);

        onView(withId(R.id.borders_linear_layout)).check(matches(hasChildCount(5)));
        ViewInteraction borderCountryName1 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 0));
        borderCountryName1.check(matches(withText("Andorra")));
        ViewInteraction borderCountryName2 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 1));
        borderCountryName2.check(matches(withText("France")));
        ViewInteraction borderCountryName3 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 2));
        borderCountryName3.check(matches(withText("Gibraltar")));
        ViewInteraction borderCountryName4 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 3));
        borderCountryName4.check(matches(withText("Portugal")));
        ViewInteraction borderCountryName5 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 4));
        borderCountryName5.check(matches(withText("Morocco")));
    }

    @Test
    public void when_border_country_is_clicked_then_country_detailed_view_is_opened() throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, "Spain");

        mActivityTestRule.launchActivity(intent);

        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Spain")));
        ViewInteraction borderCountryName1 = onView(MatcherUtils.withIndex(withId(R.id.border_text_view), 0));
        borderCountryName1.perform(click());

        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Andorra")));
    }

    private List<Country> getCountriesFromJson(String path) throws IOException {
        String json = FileUtils.getJsonFromAsset(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Country>>() {});
    }

}