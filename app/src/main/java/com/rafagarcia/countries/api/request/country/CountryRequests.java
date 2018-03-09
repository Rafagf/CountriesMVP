package com.rafagarcia.countries.api.request.country;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by glouzonf on 12/05/2015.
 */
public interface CountryRequests {

    @GET("all")
    Single<List<Country>> getAllCountries();
}
