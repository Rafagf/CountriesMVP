package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.model.Country;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public class DetailedCountryPresenter {

    private DetailedCountryMvp.View view;
    private Country country;

    public DetailedCountryPresenter(DetailedCountryMvp.View view, Country country) {
        this.view = view;
        this.country = country;
    }

    void init() {
        view.setTitle(country.getName());
        view.setFlag(country.getFlagUrl());
        view.setName(country.getName());
        view.setNativeName(country.getNativeName());
        view.setContinent(country.getContinent());
        view.setRegion(country.getRegion());
        view.setArea(country.getArea());
        view.setCapital(country.getCapital());
        view.setDemonym(country.getDemonym());
        view.setPopulation(country.getPopulation());
    }

    public void stop() {

    }
}
