package com.rafagarcia.countries.main.country;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;
import com.rafagarcia.countries.main.countrieslist.CountriesListFragment;
import com.rafagarcia.countries.model.Country;

public class CountryActivity extends AppCompatActivity implements CountryFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        try {
            String countryName = intent.getStringExtra(CountriesListActivity.COUNTRY_NAME);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, CountryFragment.newInstance(countryName)).commit();

        }
        catch (Exception e){
            Log.e("Error", e.getMessage());
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
