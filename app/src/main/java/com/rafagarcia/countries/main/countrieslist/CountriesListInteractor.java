package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountriesListInteractor {

    private CountriesLocalDataSource localDataSource;
    private CountriesRemoteDataSource remoteDataSource;
    private CountriesMemoryDataSource memoryDataSource;

    public CountriesListInteractor(CountriesLocalDataSource localDataSource, CountriesRemoteDataSource remoteDataSource, CountriesMemoryDataSource memoryDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.memoryDataSource = memoryDataSource;
    }

    public Single<List<Country>> getCountries() {
        return memoryDataSource.getCountries()
                .switchIfEmpty(localDataSource.getCountries())
                .doOnSuccess(new Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> countries) throws Exception {
                        memoryDataSource.save(countries);
                    }
                }).switchIfEmpty(remoteDataSource.getCountries())
                .doOnSuccess(new Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> countries) throws Exception {
                        memoryDataSource.save(countries);
                        localDataSource.save(countries);
                    }
                });
    }
}
