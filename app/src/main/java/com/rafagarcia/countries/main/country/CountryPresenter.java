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

    public void showCountryInformation(Country country){
        view.showFlag(country.getFlagUrl());
        view.showName(country.getName());
        view.showNativeName(country.getNativeName());
        view.showRegion(country.getRegion());
        view.showSubregion(country.getSubregion());
        view.showArea(country.getArea());
        view.showCapital(country.getCapital());
        view.showDenonym(country.getDemonym());
        view.showPopulation(country.getPopulation());

        showBorders(country);
    }

    private void showBorders(Country country) {
        if(country.getBorders().size() == 0){
            view.hideBorderCountriesText();
        }
        else {
            view.showBorderCountriesText();
            Country borderCountry;
            for (int i = 0; i < country.getBorders().size(); i++) {
                borderCountry = MyApplication.getInstance().getCountryByAlphaCode(country.getBorders().get(i));
                view.showBorderCountry(borderCountry);
            }
        }
    }
}
