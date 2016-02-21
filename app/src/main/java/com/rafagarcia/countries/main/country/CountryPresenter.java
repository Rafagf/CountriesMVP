package com.rafagarcia.countries.main.country;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public class CountryPresenter {

    CountryFragmentInterface view;

    public CountryPresenter(CountryFragmentInterface countryView) {
        view = countryView;
    }

    public void getCountryInformation(String name){
        Country country =  MyApplication.getInstance().getCountryByName(name);
        view.showCountryInformation(country);
    }
}
