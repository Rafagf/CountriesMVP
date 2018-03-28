package com.rafagarcia.countries;

import android.app.Application;

import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerApplicationComponent;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.NetworkModule;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class MyApplication extends Application {

    private final static String COUNTRIES_BASE_URL = "https://restcountries.eu/rest/v1/";
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createApplicationComponent();
    }

    void createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(COUNTRIES_BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
