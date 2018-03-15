package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rafagarcia.countries.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.appflate.restmock.RESTMockServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.appflate.restmock.utils.RequestMatchers.pathContains;

/**
 * Created by Rafa on 15/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class CountryListActivityTest {

    @Rule
    public IntentsTestRule<CountryListActivity> mActivityTestRule = new IntentsTestRule<>(CountryListActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        RESTMockServer.reset();
    }

    @Test
    public void it_shows_screen_when_launched() {
        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.list_of_countries_root)).check(matches(isDisplayed()));
    }

    @Test
    public void it_shows_countries_list_when_launched_and_no_local_data_is_stored() throws InterruptedException {
        RESTMockServer.whenGET(pathContains("restcountries.eu/rest/v1/all")).thenReturnFile(200, "json/list_of_countries.json");

        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.list_of_countries_root)).check(matches(isDisplayed()));
    }
}