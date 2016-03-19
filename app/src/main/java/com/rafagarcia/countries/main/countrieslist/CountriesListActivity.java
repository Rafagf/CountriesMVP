package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.country.CountryActivity;

public class CountriesListActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    public static final String COUNTRY = "country";
    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, CountriesListFragment.newInstance()).commit();

        setToolbar();
    }

    private void setToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        if (mToolBar != null) {
            mToolBar.setTitle(R.string.countries_title);
            setSupportActionBar(mToolBar);
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }
    }

    @Override
    public void goToSelectedCountry(String name, View flagView, View nameView,
                                    View regionView) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra(COUNTRY, MyApplication.getInstance().getCountryByName(name));

        // Get the transition name from the string
        String flagTransition = getString(R.string.flag_transition);
        String countryTransition = getString(R.string.country_name_transition);
        String regionTransition = getString(R.string.region_transition);

        Pair<View, String> flag = Pair.create(flagView, flagTransition);
        Pair<View, String> country = Pair.create( nameView, countryTransition);
        Pair<View, String> region = Pair.create(regionView, regionTransition);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, flag, country, region);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
