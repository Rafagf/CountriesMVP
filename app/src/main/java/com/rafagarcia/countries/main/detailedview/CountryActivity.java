package com.rafagarcia.countries.main.detailedview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.utilities.Utilities;
import com.rafagarcia.countries.main.countrieslist.CountryListActivity;
import com.rafagarcia.countries.model.Country;

public class CountryActivity extends AppCompatActivity{

    private final static String COUNTRY_FRAGMENT_TAG = "country_fragment";
    private Toolbar mToolBar;
    private Country mCountry;
    private CountryFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        mCountry = intent.getParcelableExtra(CountryListActivity.COUNTRY);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag(COUNTRY_FRAGMENT_TAG) == null){
            mFragment = CountryFragment.newInstance(mCountry);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, mFragment).commit();
        } else{
            mFragment = (CountryFragment)fm.findFragmentByTag(COUNTRY_FRAGMENT_TAG);
        }

        setToolbar();
    }

    private void setToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
            Utilities.setToolbarTitle(this, mToolBar, mCountry.getName());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }
}
