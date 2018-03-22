package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.main.repositories.CountriesLocalDataSource;
import com.rafagarcia.countries.main.repositories.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.repositories.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 22/03/2018.
 */
public class DetailedCountryInteractorTest {

    private CountriesMemoryDataSource memoryDataSource;
    private CountriesLocalDataSource localDataSource;
    private CountriesRemoteDataSource remoteDataSource;
    private DetailedCountryInteractor interactor;

    @Before
    public void setUp() throws Exception {
        memoryDataSource = mock(CountriesMemoryDataSource.class);
        localDataSource = mock(CountriesLocalDataSource.class);
        remoteDataSource = mock(CountriesRemoteDataSource.class);
        interactor = new DetailedCountryInteractor(localDataSource, memoryDataSource, remoteDataSource);
    }

    @Test
    public void given_memory_data_source_has_data_then_return_memory_data_source_content() throws Exception {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        TestObserver<Country> observer = new TestObserver<>();
        when(memoryDataSource.getCountry("Spain")).thenReturn(Maybe.just(country));
        when(localDataSource.getCountry("Spain")).thenReturn(Maybe.empty());
        when(remoteDataSource.getCountry("Spain")).thenReturn(Single.never());

        Maybe<Country> observable = interactor.getCountry("Spain");

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        Country receivedCountry = observer.values().get(0);
        assertEquals(receivedCountry.getName(),"Spain");
    }

    @Test
    public void given_local_data_source_has_data_then_return_local_data_source_content() throws Exception {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        TestObserver<Country> observer = new TestObserver<>();
        when(memoryDataSource.getCountry("Spain")).thenReturn(Maybe.empty());
        when(localDataSource.getCountry("Spain")).thenReturn(Maybe.just(country));
        when(remoteDataSource.getCountry("Spain")).thenReturn(Single.never());

        Maybe<Country> observable = interactor.getCountry("Spain");

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        Country receivedCountry = observer.values().get(0);
        assertEquals(receivedCountry.getName(),"Spain");
    }

    @Test
    public void given_remote_data_source_has_data_then_return_remote_data_source_content() throws Exception {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        TestObserver<Country> observer = new TestObserver<>();
        when(memoryDataSource.getCountry("Spain")).thenReturn(Maybe.empty());
        when(localDataSource.getCountry("Spain")).thenReturn(Maybe.empty());
        when(remoteDataSource.getCountry("Spain")).thenReturn(Single.just(country));

        Maybe<Country> observable = interactor.getCountry("Spain");

        observable.subscribe(observer);
        observer.assertComplete();
        observer.assertNoErrors();
        Country receivedCountry = observer.values().get(0);
        assertEquals(receivedCountry.getName(),"Spain");
    }

}