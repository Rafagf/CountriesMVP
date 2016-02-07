package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    CountriesListFragmentInterface view;

    public CountriesListPresenter(CountriesListFragmentInterface view){
        this.view = view;
    }

    public void loadCountries(){
        List<Country> countriesList = MyApplication.getInstance().getCountries();
        view.updateAdapter(countriesList);
    }

    public void countrySelected(String name) {
        int pos = MyApplication.getInstance().getCountryPosByName(name);
        if(pos >= 0) {
            view.goToSelectedCountry(name);
        }
    }
}
