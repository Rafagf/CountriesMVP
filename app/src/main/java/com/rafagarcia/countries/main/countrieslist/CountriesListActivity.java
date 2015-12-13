package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.country.CountryActivity;

public class CountriesListActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    public static final String COUNTRY_NAME = "country_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, CountriesListFragment.newInstance()).commit();
    }

    @Override
    public void goToSelectedCountry(String name) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra(COUNTRY_NAME, name);
        startActivity(intent);
    }
}
