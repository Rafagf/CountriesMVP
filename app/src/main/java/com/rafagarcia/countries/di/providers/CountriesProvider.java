package com.rafagarcia.countries.di.providers;

import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.repositories.CountriesRemoteDataSource;

/**
 * Created by Rafa on 12/03/2018.
 */

public class CountriesProvider {

    private CountriesLocalDataSource localDataSource;
    private CountriesMemoryDataSource memoryDataSource;
    private CountriesRemoteDataSource remoteDataSource;

    public CountriesProvider(CountriesLocalDataSource localDataSource, CountriesMemoryDataSource memoryDataSource, CountriesRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.memoryDataSource = memoryDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public CountriesLocalDataSource getLocalDataSource() {
        return localDataSource;
    }

    public CountriesMemoryDataSource getMemoryDataSource() {
        return memoryDataSource;
    }

    public CountriesRemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }
}
