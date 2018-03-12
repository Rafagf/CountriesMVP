package com.rafagarcia.countries.di.components;


import android.app.Application;
import android.content.Context;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.CountriesProviderModule;
import com.rafagarcia.countries.di.modules.NetworkModule;
import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, CountriesProviderModule.class})

public interface ApplicationComponent {

    Application application();

    Context getContext();

    CountryApi getCountryRequest();

    CountriesLocalDataSource getCountriesLocalDataSource();

    CountriesRemoteDataSource getCountriesRemoteDataSource();

    CountriesMemoryDataSource getCountriesMemoryDataSource();
}