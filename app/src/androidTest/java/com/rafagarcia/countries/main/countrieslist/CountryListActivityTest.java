package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.TestApplication;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.utils.FileUtils;
import com.rafagarcia.countries.main.utils.RecyclerViewItemCountAssertion;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.appflate.restmock.utils.RequestMatchers.pathEndsWith;

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
    public void it_shows_2_countries_coming_from_remote_when_launched_and_there_is_no_data_in_memory_nor_local_data_sources() throws InterruptedException {
        RESTMockServer.whenGET(pathEndsWith("all")).thenReturnFile(200, "json/list_of_countries_1.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countriesListRecyclerView)).check(new RecyclerViewItemCountAssertion(2));
    }

    @Test
    public void it_shows_12_countries_coming_from_local_when_launched_and_there_is_data_in_local_data_source() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_2.json");
        countriesLocalDataSource.save(countries);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countriesListRecyclerView)).check(new RecyclerViewItemCountAssertion(12));
    }

    @Test
    public void it_shows_6_countries_coming_from_memory_when_launched_and_there_is_data_in_memory_data_source() throws IOException {
        List<Country> countries = getCountriesFromJson("json/list_of_countries_3.json");
        countriesMemoryDataSource.save(countries);

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.countriesListRecyclerView)).check(new RecyclerViewItemCountAssertion(6));
    }

    private List<Country> getCountriesFromJson(String path) throws IOException {
        String json = FileUtils.getJsonFromAsset(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Country>>() {});
    }
}