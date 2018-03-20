package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.di.providers.CountriesProvider;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.main.detailedview.DetailedCountryInteractor;
import com.rafagarcia.countries.main.detailedview.DetailedCountryMvp;
import com.rafagarcia.countries.main.detailedview.DetailedCountryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class DetailedCountryViewModule {

    private DetailedCountryMvp.View view;

    public DetailedCountryViewModule(DetailedCountryMvp.View view) {
        this.view = view;
    }

    @Provides
    public DetailedCountryInteractor provideInteractor(CountriesProvider countriesProvider) {
        return new DetailedCountryInteractor(countriesProvider.getLocalDataSource(), countriesProvider.getMemoryDataSource());
    }

    @Provides
    public DetailedCountryPresenter providePresenter(DetailedCountryInteractor interactor, ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        return new DetailedCountryPresenter(view, interactor, resourcesProvider, flagProvider);
    }
}
