package com.rafagarcia.countries.countrieslist;

import android.content.Intent;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    CountriesListInteractor interactor;
    CountriesListFragmentInterface view;

    public CountriesListPresenter(CountriesListFragmentInterface view, CountriesListInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void loadCountries(){
        interactor.loadCountries(this);
    }

    public void onCountriesReady(List<Country> countryList){
        view.updateAdapter(countryList);
    }

    public void countrySelected(String name) {
        int pos = MyApplication.getInstance().getCountryPosByName(name);
        if(pos >= 0) {
            view.goToSelectedCountry(name);
        }
    }
}
