package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.model.Country;

/**
 * Created by Rafa on 09/03/2018.
 */

class CountryViewHolderPresenter {

    CountryViewHolder view;
    Country country;

    public CountryViewHolderPresenter(CountryViewHolder view) {
        this.view = view;
    }

    public void bind(Country country) {
        this.country = country;
        view.setName(country.getName());
        view.setRegion(country.getRegion());
        view.setPopulation(country.getPopulation());
        view.setFlag("");
    }
}
