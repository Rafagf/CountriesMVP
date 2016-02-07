package com.rafagarcia.countries.main.launcher;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.Utilities.Utilities;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;
import com.rafagarcia.countries.model.Country;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class LauncherActivity extends Activity implements LauncherActivityInterface {

    LauncherPresenter presenter;
    LauncherInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
    }

    private void init() {
        interactor = new LauncherInteractor();
        presenter = new LauncherPresenter(this, interactor);
        fetchCountriesInfo();
    }

    @Override
    public void fetchCountriesInfo() {
        boolean networkAvailable = Utilities.isNetworkAvailable(getApplicationContext());
        String countriesFromCache = Utilities.getCountriesJsonFromSharedPreferences(getApplicationContext());
        presenter.fetchCountriesInfo(networkAvailable, countriesFromCache);
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
        Toast.makeText(getApplicationContext(), "No internet connection, please try again later", Toast.LENGTH_LONG).show();
    }
}
