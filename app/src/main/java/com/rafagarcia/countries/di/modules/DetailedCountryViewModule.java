package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.main.detailedview.DetailedCountryMvp;
import com.rafagarcia.countries.main.detailedview.DetailedCountryPresenter;
import com.rafagarcia.countries.model.Country;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class DetailedCountryViewModule {

    private DetailedCountryMvp.View view;
    private Country country;

    public DetailedCountryViewModule(DetailedCountryMvp.View view, Country country) {
        this.view = view;
        this.country = country;
    }

    @Provides
    public DetailedCountryPresenter providePresenter(ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        return new DetailedCountryPresenter(view, country, resourcesProvider, flagProvider);
    }
}
