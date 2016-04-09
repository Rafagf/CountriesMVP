package com.rafagarcia.countries.main.country;

import com.rafagarcia.countries.model.Country;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public interface CountryFragmentInterface {
    void showFlag(String flagUrl);
    void showName(String name);
    void showRegion(String region);
    void showSubregion(String subregion);
    void showCapital(String capital);
    void showPopulation(String population);
    void showArea(String area);
    void showDenonym(String denonym);
    void showNativeName(String nativeName);
    void showBorderCountry(Country country);
    void hideBorderCountriesText();
    void showBorderCountriesText();
}