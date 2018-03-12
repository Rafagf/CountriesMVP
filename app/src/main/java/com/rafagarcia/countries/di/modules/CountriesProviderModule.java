package com.rafagarcia.countries.di.modules;

import android.content.Context;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class CountriesProviderModule {

    public CountriesProviderModule() {
    }

    @Provides
    @Singleton
    CountriesProvider provideCountriesProvider(CountryApi countryApi, Context context) {
        return new CountriesProvider(
                new CountriesLocalDataSource(context),
                new CountriesMemoryDataSource(),
                new CountriesRemoteDataSource(countryApi));
    }
}
