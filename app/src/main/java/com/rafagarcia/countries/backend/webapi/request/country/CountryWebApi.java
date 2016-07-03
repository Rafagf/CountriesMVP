package com.rafagarcia.countries.backend.webapi.request.country;

import com.rafagarcia.countries.backend.webapi.BaseWebApi;
import com.rafagarcia.countries.backend.webapi.HttpLoggingInterceptor;
import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by glouzaille on 06/05/2015.
 */
public class CountryWebApi extends BaseWebApi {

    private static final String BASE_URL = "https://restcountries.eu/rest/v1/";
    private OkHttpClient mOkHttpClient;
    private HttpLoggingInterceptor mLogging;
    private Retrofit mRetrofit;
    private CountryRequestsInterface mRequestsService;

    public CountryWebApi() {
        getRequestsService();
    }

    public Observable<List<Country>> getCountries() {
        return mRequestsService.getAllCountries();
    }

    public Observable<List<Country>> getCountryFullText(String name) {
        return mRequestsService.getCountryFullText(name);
    }

    private void getRequestsService() {
        mOkHttpClient = new OkHttpClient();
        mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient.interceptors().add(mLogging);

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mRequestsService =  mRetrofit.create(CountryRequestsInterface.class);
    }
}
