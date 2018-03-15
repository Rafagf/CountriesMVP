package com.rafagarcia.countries.di.components;


import android.content.Context;
import android.content.SharedPreferences;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.CountriesProviderModule;
import com.rafagarcia.countries.di.modules.FlagProviderModule;
import com.rafagarcia.countries.di.modules.NetworkModule;
import com.rafagarcia.countries.di.modules.TextProviderModule;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TextProviderModule.class, FlagProviderModule.class, NetworkModule.class, CountriesProviderModule.class})

public interface ApplicationComponent {

    Context getContext();

    ResourcesProvider getTextProvider();

    FlagProvider getFlagProvider();

    CountriesProvider getCountriesProvider();

    CountryApi getCountryApi();

    SharedPreferences getSharedPreferences();
}