package com.rafagarcia.countries.main.launcher;

import com.rafagarcia.countries.backend.CountryResponse;
import com.rafagarcia.countries.backend.RetrofitService;

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
        Call<List<CountryResponse>> call = retrofitService.getAllCountries();

        call.enqueue(new Callback<List<CountryResponse>>() {
            @Override
            public void onResponse(Response<List<CountryResponse>> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    launcherPresenter.countriesFetchedSuccessfully(response.body());
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
