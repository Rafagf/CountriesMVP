package com.rafagarcia.countries.backend.webapi.request.country;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by glouzonf on 12/05/2015.
 */
public interface CountryRequestsInterface {

    @GET("all")
    Observable<List<Country>> getAllCountries();

    @GET("name/{countryName}")
    Observable<List<Country>> getCountryFullText(@Path("countryName") String name);

}
