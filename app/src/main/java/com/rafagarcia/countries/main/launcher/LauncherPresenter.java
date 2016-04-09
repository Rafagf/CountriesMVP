package com.rafagarcia.countries.main.launcher;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.backend.CountryResponse;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherPresenter {
    LauncherActivityInterface mView;
    LauncherInteractor mInteractor;

    public LauncherPresenter(LauncherActivityInterface view, LauncherInteractor interactor){
        this.mView = view;
        this.mInteractor = interactor;
    }

    public void fetchCountriesInfo(boolean networkAvailable, String jsonFromCache) {
        if(networkAvailable){
            mInteractor.fetchCountriesInfo(this);
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
        mView.goToMainScreen();
    }

    public void onErrorFetchingCountries() {
        mView.showError();
    }

    public void loadCountriesInApp(List<Country> countries){
        MyApplication.getInstance().loadCountries(countries);
    }
}
