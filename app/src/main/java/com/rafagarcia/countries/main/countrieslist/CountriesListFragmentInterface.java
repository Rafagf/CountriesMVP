package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public interface CountriesListFragmentInterface {

    List<Country> getCountryList();
    void updateAdapter(List<Country> countryList);
    void goToSelectedCountry(String name);
}
