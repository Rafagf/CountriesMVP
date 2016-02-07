package com.rafagarcia;

import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;

/**
 * Created by rafagarcia on 07/02/2016.
 */
public interface RetrofitService {
    String BASE_URL = "https://restcountries.eu/rest/v1/";

    @GET("all")
    Call<Response<Country>> getAllCountries();
}