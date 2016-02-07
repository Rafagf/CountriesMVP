package com.rafagarcia.countries.main.countrieslist;

import android.view.View;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public interface CountriesAdapterInterface {
    void countrySelected(String name, View flagView, View nameView, View regionView);
}
