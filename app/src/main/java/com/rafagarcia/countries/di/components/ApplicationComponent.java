package com.rafagarcia.countries.di.components;


import android.content.Context;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.CountriesProviderModule;
import com.rafagarcia.countries.di.modules.FlagProviderModule;
import com.rafagarcia.countries.di.modules.NetworkModule;
import com.rafagarcia.countries.di.modules.TextProviderModule;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.TextProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TextProviderModule.class, FlagProviderModule.class, NetworkModule.class, CountriesProviderModule.class})

public interface ApplicationComponent {

    Context getContext();

    TextProvider getTextProvider();

    FlagProvider getFlagProvider();

    CountriesProvider getCountriesProvider();

    CountryApi getCountryApi();
}