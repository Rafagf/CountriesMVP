package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    CountriesListFragmentInterface view;
    List<Country> countryList;
    public CountriesListPresenter(CountriesListFragmentInterface view){
        this.view = view;
    }

    public void loadCountries(){
        countryList = MyApplication.getInstance().getCountries();
        view.updateAdapter(countryList);
    }

    public void countrySelected(String name) {
        int pos = MyApplication.getInstance().getCountryPosByName(name);
        if(pos >= 0) {
            view.goToSelectedCountry(name);
        }
    }

    public void onQueryTextSubmit(String query) {
        search(query);
    }

    public void onQueryTextChange(String newText) {
        search(newText);
    }

    public void search(String query){
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : countryList) {
            if(country.getName().toLowerCase().startsWith(query.toLowerCase())){
                filteredCountries.add(country);
            }
        }
        view.updateAdapter(filteredCountries);
    }
}
