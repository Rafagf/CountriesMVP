package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.model.Country;

/**
 * Created by Rafa on 09/03/2018.
 */

class CountryViewHolderPresenter {

    private final String FLAGS_URL = "http://www.geonames.org/flags/x/";
    CountryViewHolder view;
    Country country;

    public CountryViewHolderPresenter(CountryViewHolder view) {
        this.view = view;
    }

    public void bind(Country country) {
        this.country = country;
        view.setName(country.getName());
        view.setRegion("Region: " + country.getRegion());
        view.setPopulation("Population: " + country.getPopulation());
        view.setFlag(FLAGS_URL + country.getAlpha2Code().toLowerCase() + ".gif");
    }
}
