package com.rafagarcia.countries.countrieslist;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.List;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListInteractor {
    public void loadCountries(CountriesListPresenter presenter) {
        List<Country> countriesList = MyApplication.getInstance().getCountries();
        presenter.onCountriesReady(countriesList);
    }
}
