package com.rafagarcia.countries.di.modules;

import android.content.SharedPreferences;

import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.repositories.CountriesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class CountriesProviderModule {

    @Provides
    @Singleton
    CountriesProvider provideCountriesProvider(CountryApi countryApi, SharedPreferences sharedPreferences) {
        return new CountriesProvider(
                new CountriesLocalDataSource(sharedPreferences),
                new CountriesMemoryDataSource(),
                new CountriesRemoteDataSource(countryApi));
    }
}
