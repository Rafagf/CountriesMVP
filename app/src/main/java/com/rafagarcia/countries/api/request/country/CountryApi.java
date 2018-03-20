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
    Single<List<Country>> getCountry(@Path("country") String name);
}
