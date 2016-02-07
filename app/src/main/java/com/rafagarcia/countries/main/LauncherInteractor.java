package com.rafagarcia.countries.main;

import android.util.Log;
import android.widget.Toast;

import com.rafagarcia.RetrofitService;
import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherInteractor {



    public void fetchCountriesInfo(LauncherPresenter launcherPresenter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Response<Country>> call = retrofitService.getAllCountries();

        call.enqueue(new Callback<Response<Country>>() {
            @Override
            public void onResponse(Response<Response<Country>> response, Retrofit retrofit) {
                Log.d("Retrofit", String.valueOf(response.code()));
                Response<Country> countryResponse = response.body();
                Country country = countryResponse.body();
                Log.d("Country", country.getName());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
