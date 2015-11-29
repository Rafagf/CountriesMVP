package com.rafagarcia.countries.countrieslist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rafagarcia.countries.R;

public class CountriesListActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, CountriesListFragment.newInstance()).commit();

    }

    @Override
    public void goToSelectedCountry(String name) {
        Toast.makeText(this, "Going to " + name, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, CountryActivity.class);
//        intent.putExtra("country_name", name);
//        startActivity(intent);
    }
}
