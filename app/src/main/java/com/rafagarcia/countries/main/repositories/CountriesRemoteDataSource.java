package com.rafagarcia.countries.main.repositories;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountriesRemoteDataSource {

    private CountryApi countryApi;

    public CountriesRemoteDataSource(CountryApi countryApi) {
        this.countryApi = countryApi;
    }

    public Single<List<Country>> getCountries() {
        return countryApi.getAllCountries();
    }

    public Single<Country> getCountryByName(String name) {
        return countryApi.getCountryByName(name).flatMap(countries -> Single.just(countries.get(0)));
    }

    public Single<Country> getCountryByAlpha3(String alpha) {
        return countryApi.getCountryByAlpha3(alpha);
    }
}
