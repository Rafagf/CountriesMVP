package com.rafagarcia.countries.main;

import android.util.Log;
import android.widget.Toast;

import com.rafagarcia.RetrofitService;
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
        Call<ResponseBody> call = retrofitService.getAllCountries();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                Log.d("Retrofit", "Success");
                Log.d("Retrofit", String.valueOf(response.code()));
                try {
                    Log.d("Retrofit", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Retrofit", "Failed");
            }
        });
    }
}
