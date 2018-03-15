package com.rafagarcia.countries.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafa on 04/11/2017.
 */

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    SharedPreferences provideSharesPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
