package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.main.countrieslist.CountryListInteractor;
import com.rafagarcia.countries.main.countrieslist.CountryListMvp;
import com.rafagarcia.countries.main.countrieslist.CountryListPresenter;
import com.rafagarcia.countries.di.providers.CountriesProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class CountryListViewModule {

    private CountryListMvp.View view;

    public CountryListViewModule(CountryListMvp.View view) {
        this.view = view;
    }

    @Provides
    public CountryListInteractor provideInteractor(CountriesProvider countriesProvider) {
        return new CountryListInteractor(countriesProvider.getLocalDataSource(), countriesProvider.getRemoteDataSource(), countriesProvider.getMemoryDataSource());
    }

    @Provides
    public CountryListPresenter providePresenter(CountryListInteractor interactor) {
        return new CountryListPresenter(view, interactor);
    }
}
