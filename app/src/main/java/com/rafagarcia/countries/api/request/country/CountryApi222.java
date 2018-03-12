package com.rafagarcia.countries.api.request.country;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CountryApi222 {

    private static final String BASE_URL = "https://restcountries.eu/rest/v1/";
    private Retrofit retrofit;
    private CountryApi mRequestsService;

    public CountryApi222() {
        getRequestsService();
    }

    public Single<List<Country>> getCountries() {
        return mRequestsService.getAllCountries();
    }

    private void getRequestsService() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mRequestsService = retrofit.create(CountryApi.class);
    }
}
