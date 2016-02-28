package com.rafagarcia.countries.main.country;

import android.content.res.Resources;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

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
    void showBorderCountries();
}