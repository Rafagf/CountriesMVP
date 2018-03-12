package com.rafagarcia.countries.di.modules;

import android.content.Context;

import com.rafagarcia.countries.di.providers.TextProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class TextProviderModule {

    @Provides
    @Singleton
    TextProvider provideTextProvider(Context context) {
        return new TextProvider(context);
    }
}
