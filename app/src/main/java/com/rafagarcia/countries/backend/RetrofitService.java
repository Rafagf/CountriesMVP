package com.rafagarcia.countries.backend;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by rafagarcia on 07/02/2016.
 */
public interface RetrofitService {
    String BASE_URL = "https://restcountries.eu/rest/v1/";

    @GET("all")
    Call<List<CountryResponse>> getAllCountries();
}