package com.rafagarcia.countries;

import android.app.Application;

import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerApplicationComponent;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.CountriesProviderModule;
import com.rafagarcia.countries.di.modules.NetworkModule;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createApplicationComponent();
    }

    private void createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .countriesProviderModule(new CountriesProviderModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
