package com.rafagarcia.countries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rafagarcia.countries.countrieslist.CountriesListFragment;

public class MainActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, CountriesListFragment.newInstance()).commit();

    }

    @Override
    public void goToSelectedCountry() {

    }
}
