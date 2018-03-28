package com.rafagarcia.countries.main.detailedview;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafa on 20/03/2018.
 */

public class DetailedCountryViewModelMapper {

    public DetailedCountryViewModel mapFrom(Country country) {
        LatLng latLng = new LatLng(country.getLatlng().get(0),
                country.getLatlng().get(1));

        return new DetailedCountryViewModel.DetailedCountryViewModelBuilder()
                .setName(country.getName())
                .setAlpha2Code(country.getAlpha2Code())
                .setAlpha3Code(country.getAlpha3Code())
                .setCapital(country.getCapital())
                .setContinent(country.getContinent())
                .setRegion(country.getRegion())
                .setArea(country.getArea())
                .setNativeName(country.getNativeName())
                .setPopulation(country.getPopulation())
                .setDemonym(country.getDemonym())
                .setLatLng(latLng)
                .setBorderCountryAlphaList(country.getBorderCountryAlphaList())
                .build();
    }
}
