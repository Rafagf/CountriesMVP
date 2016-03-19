package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.Utilities.Utilities;
import com.rafagarcia.countries.main.country.CountryActivity;

public class CountriesListActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    private static final String COUNTRIES_LIST_FRAGMENT_TAG = "countries_list_fragment";
    public static final String COUNTRY = "country";
    private Toolbar mToolBar;
    private CountriesListFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag(COUNTRIES_LIST_FRAGMENT_TAG) == null){
            mFragment = CountriesListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, mFragment).commit();
        }
        else{
            mFragment = (CountriesListFragment)fm.findFragmentByTag(COUNTRIES_LIST_FRAGMENT_TAG);
        }

        setToolbar();
    }

    /**
     * There is a bug in the support library. The toolbar title won't
     * show up inside the CollapsingToolbarLayout
     */
    private void setToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        if(mToolBar != null) {
            Utilities.setToolbarTitle(this, mToolBar, getResources().getString(R.string.countries_title));
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
