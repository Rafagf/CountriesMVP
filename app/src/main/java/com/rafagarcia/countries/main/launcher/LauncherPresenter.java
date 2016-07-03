package com.rafagarcia.countries.main.launcher;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherPresenter {
    LauncherActivityInterface mView;
    LauncherInteractor mInteractor;

    public LauncherPresenter(LauncherActivityInterface view, LauncherInteractor interactor){
        this.mView = view;
        this.mInteractor = interactor;
    }

    public void fetchCountriesInfo(boolean networkAvailable, String jsonFromCache) {
        if(networkAvailable){
            mInteractor.getAllCountries(new Subscriber<List<Country>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    onErrorFetchingCountries();
                }

                @Override
                public void onNext(List<Country> countries) {
                    loadCountriesInApp(countries);
                    mView.goToMainScreen();
                }
            });
        }
    }

    public void onErrorFetchingCountries() {
        mView.showError();
    }

    public void loadCountriesInApp(List<Country> countries){
        MyApplication.getInstance().loadCountries(countries);
    }
}
