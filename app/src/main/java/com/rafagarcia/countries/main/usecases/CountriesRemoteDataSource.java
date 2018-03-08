package com.rafagarcia.countries.main.usecases;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountriesRemoteDataSource {

    CountryApi countryApi;

    public CountriesRemoteDataSource(CountryApi countryApi) {
        this.countryApi = countryApi;
    }

    public Single<List<Country>> getCountries() {
        return countryApi.getCountries();
    }
}
