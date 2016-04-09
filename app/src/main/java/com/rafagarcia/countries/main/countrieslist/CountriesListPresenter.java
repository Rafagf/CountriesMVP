package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    CountriesListFragmentInterface mView;
    List<Country> mCountryList;

    public CountriesListPresenter(CountriesListFragmentInterface view){
        this.mView = view;
    }

    public void loadCountries() {
        mCountryList = MyApplication.getInstance().getCountries();
        mView.updateAdapter(mCountryList);
    }

    public void countrySelected(String name) {
        int pos = MyApplication.getInstance().getCountryPosByName(name);
        if(pos >= 0) {
            mView.goToSelectedCountry(name);
        }
    }

    public void onQueryTextSubmit(String query) {
        search(query);
    }

    public void onQueryTextChange(String newText) {
        search(newText);
    }

    public void search(String query) {
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : mCountryList) {
            if(country.getName().toLowerCase().startsWith(query.toLowerCase())){
                filteredCountries.add(country);
            }
        }
        mView.updateAdapter(filteredCountries);
    }
}
