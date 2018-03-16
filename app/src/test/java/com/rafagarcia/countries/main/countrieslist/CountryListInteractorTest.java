package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.repositories.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 14/03/2018.
 */
public class CountryListInteractorTest {

    private CountriesMemoryDataSource memoryDataSource;
    private CountriesLocalDataSource localDataSource;
    private CountriesRemoteDataSource remoteDataSource;
    private CountryListInteractor interactor;

    @Before
    public void setUp() throws Exception {
        memoryDataSource = mock(CountriesMemoryDataSource.class);
        localDataSource = mock(CountriesLocalDataSource.class);
        remoteDataSource = mock(CountriesRemoteDataSource.class);
        interactor = new CountryListInteractor(localDataSource, remoteDataSource, memoryDataSource);
    }

    @Test
    public void given_memory_data_source_has_data_then_return_memory_data_source_content() throws Exception {
        List<Country> countryList = new ArrayList<>();
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Fake country");
        countryList.add(country);
        TestObserver<List<Country>> observer = new TestObserver<>();
        when(memoryDataSource.getCountries()).thenReturn(Maybe.just(countryList));
        when(localDataSource.getCountries()).thenReturn(Maybe.empty());
        when(remoteDataSource.getCountries()).thenReturn(Single.never());

        Maybe<List<Country>> observable = interactor.getCountries();

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        List<Country> receivedList = observer.values().get(0);
        assertEquals(receivedList.get(0).getName(),"Fake country");
    }

    @Test
    public void given_local_data_source_has_data_then_return_local_data_source_content() throws Exception {
        List<Country> countryList = new ArrayList<>();
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Fake country");
        countryList.add(country);
        TestObserver<List<Country>> observer = new TestObserver<>();
        when(memoryDataSource.getCountries()).thenReturn(Maybe.empty());
        when(localDataSource.getCountries()).thenReturn(Maybe.just(countryList));
        when(remoteDataSource.getCountries()).thenReturn(Single.never());

        Maybe<List<Country>> observable = interactor.getCountries();

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        List<Country> receivedList = observer.values().get(0);
        assertEquals(receivedList.get(0).getName(),"Fake country");
    }

    @Test
    public void given_remote_data_source_has_data_then_return_remote_data_source_content() throws Exception {
        List<Country> countryList = new ArrayList<>();
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Fake country");
        countryList.add(country);
        TestObserver<List<Country>> observer = new TestObserver<>();
        when(memoryDataSource.getCountries()).thenReturn(Maybe.empty());
        when(localDataSource.getCountries()).thenReturn(Maybe.empty());
        when(remoteDataSource.getCountries()).thenReturn(Single.just(countryList));

        Maybe<List<Country>> observable = interactor.getCountries();

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        List<Country> receivedList = observer.values().get(0);
        assertEquals(receivedList.get(0).getName(),"Fake country");
    }
}