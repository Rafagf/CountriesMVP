package com.rafagarcia.countries.main.launcher;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.backend.CountryResponse;
import com.rafagarcia.countries.main.launcher.LauncherActivityInterface;
import com.rafagarcia.countries.main.launcher.LauncherInteractor;
import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherPresenter {
    LauncherActivityInterface view;
    LauncherInteractor interactor;

    public LauncherPresenter(LauncherActivityInterface view, LauncherInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchCountriesInfo(boolean networkAvailable, String jsonFromCache) {
        if(networkAvailable){
            interactor.fetchCountriesInfo(this);
        }
        else if(jsonFromCache != null){
            //todo implement caching (app shoud work offline)
            onErrorFetchingCountries();
        }
        else{
            onErrorFetchingCountries();
        }
    }

    public void countriesFetchedSuccessfully(List<CountryResponse> countriesResponse) {
        List<Country> countryList = new ArrayList<>();
        for(int i = 0; i < countriesResponse.size(); i++){
            countryList.add(new Country(countriesResponse.get(i)));
        }
        loadCountriesInApp(countryList);
        view.goToMainScreen();
    }

    public void onErrorFetchingCountries() {
        view.showError();
    }

    public void loadCountriesInApp(List<Country> countries){
        MyApplication.getInstance().loadCountries(countries);
    }
}
