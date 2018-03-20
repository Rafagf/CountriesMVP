package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.model.Country;

import io.reactivex.Maybe;

/**
 * Created by Rafa on 08/03/2018.
 */

public class DetailedCountryInteractor implements DetailedCountryMvp.Interactor {

    private CountriesMemoryDataSource memoryDataSource;
    private CountriesLocalDataSource localDataSource;

    public DetailedCountryInteractor(CountriesLocalDataSource countriesLocalDataSource, CountriesMemoryDataSource memoryDataSource) {
        this.localDataSource = countriesLocalDataSource;
        this.memoryDataSource = memoryDataSource;
    }

    @Override
    public Maybe<Country> getCountry(String name) {
        Maybe<Country> memorySource = memoryDataSource.getCountry(name);
        Maybe<Country> localSource = localDataSource.getCountry(name);

        //todo i guess we should add the remote although without push notification will never be necessary
        return Maybe.concat(memorySource, localSource).firstElement();
    }
}
