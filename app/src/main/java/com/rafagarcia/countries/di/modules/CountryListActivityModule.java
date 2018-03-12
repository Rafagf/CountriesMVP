package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.main.countrieslist.CountryListInteractor;
import com.rafagarcia.countries.main.countrieslist.CountryListMvp;
import com.rafagarcia.countries.main.countrieslist.CountryListPresenter;
import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class CountryListActivityModule {

    private CountryListMvp.View view;

    public CountryListActivityModule(CountryListMvp.View view) {
        this.view = view;
    }

    @Provides
    public CountryListInteractor provideInteractor(CountriesLocalDataSource localDataSource, CountriesMemoryDataSource memoryDataSource, CountriesRemoteDataSource remoteDataSource) {
        return new CountryListInteractor(localDataSource, remoteDataSource, memoryDataSource);
    }

    @Provides
    public CountryListPresenter providePresenter(CountryListInteractor interactor) {
        return new CountryListPresenter(view, interactor);
    }
}
