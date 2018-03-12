package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.api.request.country.CountryApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://restcountries.eu/rest/v1/";
    private Retrofit retrofit;

    public NetworkModule() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    CountryApi provideCountryRequest() {
        return retrofit.create(CountryApi.class);
    }
}
