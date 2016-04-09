package com.rafagarcia.countries.main.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.Utilities.Utilities;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class LauncherActivity extends Activity implements LauncherActivityInterface {

    LauncherPresenter mPresenter;
    LauncherInteractor mInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
    }

    private void init() {
        mInteractor = new LauncherInteractor();
        mPresenter = new LauncherPresenter(this, mInteractor);
        fetchCountriesInfo();
    }

    @Override
    public void fetchCountriesInfo() {
        boolean networkAvailable = Utilities.isNetworkAvailable(getApplicationContext());
        String countriesFromCache = Utilities.getCountriesJsonFromSharedPreferences(getApplicationContext());
        mPresenter.fetchCountriesInfo(networkAvailable, countriesFromCache);
    }

    @Override
    public void goToMainScreen() {
        Intent intent = new Intent(LauncherActivity.this, CountriesListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError() {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.internet_error, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCountriesInfo();
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
