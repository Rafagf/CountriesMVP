package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.repositories.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
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
    public Single<Country> getCountry(String name) {
        Maybe<Country> memorySource = memoryDataSource.getCountryByName(name);
        Maybe<Country> localSource = localDataSource.getCountryByName(name);
        Single<Country> remoteSource = remoteDataSource.getCountryByName(name);
        return Maybe.concat(memorySource, localSource, remoteSource.toMaybe()).firstElement().toSingle();
    }

    @Override
    public Single<List<String>> getBorderCountriesName(List<String> alphaCountryList) {
        return Observable.fromIterable(alphaCountryList)
                .flatMap((String countryAlpha) -> {
                    Maybe<Country> memorySource = memoryDataSource.getCountryByAlpha3(countryAlpha);
                    Maybe<Country> localSource = localDataSource.getCountryByAlpha3(countryAlpha);
                    Single<Country> remoteSource = remoteDataSource.getCountryByAlpha3(countryAlpha);

                    return Maybe.concat(memorySource, localSource, remoteSource.toMaybe())
                            .firstElement()
                            .toObservable()
                            .flatMap(country -> {
                                String countryName = country.getName();
                                return Observable.just(countryName);
                            });
                }).toList();
    }
}
