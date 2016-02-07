package com.rafagarcia.countries.main.launcher;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.RetrofitService;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherInteractor {



    public void fetchCountriesInfo(final LauncherPresenter launcherPresenter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call = retrofitService.getAllCountries();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    launcherPresenter.countriesFetchedSuccessfully(response);
                } else {
                    launcherPresenter.onErrorFetchingCountries();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                launcherPresenter.onErrorFetchingCountries();
            }
        });
    }
}
