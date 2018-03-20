package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.repositories.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Rafa on 08/03/2018.
 */

public class DetailedCountryInteractor implements DetailedCountryMvp.Interactor {

    private CountriesMemoryDataSource memoryDataSource;
    private CountriesLocalDataSource localDataSource;
    private CountriesRemoteDataSource remoteDataSource;

    public DetailedCountryInteractor(CountriesLocalDataSource countriesLocalDataSource, CountriesMemoryDataSource memoryDataSource, CountriesRemoteDataSource remoteDataSource) {
        this.localDataSource = countriesLocalDataSource;
        this.memoryDataSource = memoryDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Maybe<Country> getCountry(String name) {
        Maybe<Country> memorySource = memoryDataSource.getCountry(name);
        Maybe<Country> localSource = localDataSource.getCountry(name);
        Single<Country> remoteSource = remoteDataSource.getCountry(name);

        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement();
    }
}
