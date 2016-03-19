package com.rafagarcia.countries.main.country;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;
import com.rafagarcia.countries.main.countrieslist.CountriesListFragment;
import com.rafagarcia.countries.model.Country;

public class CountryActivity extends AppCompatActivity implements CountryFragment.OnFragmentInteractionListener{

    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        try {
            Country country = intent.getParcelableExtra(CountriesListActivity.COUNTRY);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, CountryFragment.newInstance(country)).commit();
        }
        catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        setToolbar();
    }

    private void setToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        if (mToolBar != null) {
            mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.countries_title));
            setSupportActionBar(mToolBar);
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }
}
