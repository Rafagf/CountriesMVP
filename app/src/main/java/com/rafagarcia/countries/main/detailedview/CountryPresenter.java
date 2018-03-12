package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.model.Country;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public class CountryPresenter {

    CountryFragmentInterface mView;

    public CountryPresenter(CountryFragmentInterface countryView) {
        mView = countryView;
    }

    public void showCountryInformation(Country country) {
        mView.showFlag(country.getFlagUrl());
        mView.showName(country.getName());
        mView.showNativeName(country.getNativeName());
        mView.showRegion(country.getRegion());
        mView.showSubregion(country.getSubregion());
        mView.showArea(country.getArea());
        mView.showCapital(country.getCapital());
        mView.showDenonym(country.getDemonym());
        mView.showPopulation(country.getPopulation());
        showBorders(country);
    }

    private void showBorders(Country country) {
//        if(country.getBorders().size() == 0){
//            mView.hideBorderCountriesText();
//        } else {
//            mView.showBorderCountriesText();
//            Country borderCountry;
//            for (int i = 0; i < country.getBorders().size(); i++) {
//                borderCountry = MyApplication.getInstance().getCountryByAlphaCode(country.getBorders().get(i));
//                mView.showBorderCountry(borderCountry);
//            }
//        }
    }
}
