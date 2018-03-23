package com.rafagarcia.countries.api.request.country;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryApi {

    @GET("all")
    Single<List<Country>> getAllCountries();

    @GET("name/{country}")
    Single<List<Country>> getCountryByName(@Path("country") String name);

    @GET("alpha/{countryAlpha}")
    Single<Country> getCountryByAlpha3(@Path("countryAlpha") String alpha);
}
