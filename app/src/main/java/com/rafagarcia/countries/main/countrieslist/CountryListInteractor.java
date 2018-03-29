package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountryListInteractor implements CountryListMvp.Interactor {

    private CountriesLocalDataSource localDataSource;
    private CountriesRemoteDataSource remoteDataSource;
    private CountriesMemoryDataSource memoryDataSource;

    public CountryListInteractor(CountriesLocalDataSource localDataSource, CountriesRemoteDataSource remoteDataSource, CountriesMemoryDataSource memoryDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.memoryDataSource = memoryDataSource;
    }

    @Override
    public Maybe<List<Country>> getCountries() {
        Maybe<List<Country>> memorySource = memoryDataSource.getCountries();
        Maybe<List<Country>> localSource = localDataSource.getCountries().doOnSuccess(countries ->
                memoryDataSource.save(countries));
        Single<List<Country>> remoteSource = remoteDataSource.getCountries().doOnSuccess(countries -> {
            memoryDataSource.save(countries);
            localDataSource.save(countries);
        });

        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement();
    }
}
