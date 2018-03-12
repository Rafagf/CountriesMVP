package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.main.countrieslist.holder.CountryListViewHolderMvp;
import com.rafagarcia.countries.main.countrieslist.holder.CountryListViewHolderPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class CountryListViewHolderModule {

    private CountryListViewHolderMvp.View view;

    public CountryListViewHolderModule(CountryListViewHolderMvp.View view) {
        this.view = view;
    }

    @Provides
    public CountryListViewHolderPresenter providePresenter(ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        return new CountryListViewHolderPresenter(view, resourcesProvider, flagProvider);
    }
}
