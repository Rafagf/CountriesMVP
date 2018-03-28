package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafa on 20/03/2018.
 */

public class CountryListViewModelMapper {

    public CountryListViewModel mapFrom(Country country) {
        return new CountryListViewModel.CountryListViewModelBuilder()
                .setName(country.getName())
                .setAlpha2Code(country.getAlpha2Code())
                .setContinent(country.getContinent())
                .setPopulation(country.getPopulation())
                .build();
    }

    public List<CountryListViewModel> mapFrom(List<Country> countryList) {
        List<CountryListViewModel> countryViewModelList = new ArrayList<>();
        for (Country country : countryList) {
            countryViewModelList.add(mapFrom(country));
        }

        return countryViewModelList;
    }
}
