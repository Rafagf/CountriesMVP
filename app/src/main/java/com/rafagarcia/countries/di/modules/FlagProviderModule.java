package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.di.providers.FlagProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class FlagProviderModule {

    @Provides
    @Singleton
    FlagProvider provideFlagProvider() {
        return new FlagProvider();
    }
}
