package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 14/03/2018.
 */
public class CountryListViewHolderPresenterTest {

    CountryListViewHolderMvp.View view;
    CountryListViewHolderPresenter presenter;
    FlagProvider flagProvider;
    ResourcesProvider resourcesProvider;

    @Before
    public void setUp() throws Exception {
        resourcesProvider = mock(ResourcesProvider.class);
        flagProvider = mock(FlagProvider.class);
        view = mock(CountryListViewHolderMvp.View.class);
        presenter = new CountryListViewHolderPresenter(view, resourcesProvider, flagProvider);
    }

    @Test
    public void given_country_then_it_shows_country_name() throws Exception {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        presenter.bind(country);
        verify(view).setName("Spain");
    }

    @Test
    public void given_country_with_continent_then_it_shows_continent_name() throws Exception {
        Country country = mock(Country.class);
        when(country.getContinent()).thenReturn("Europe");
        presenter.bind(country);
        verify(view).setContinent("Europe");
    }

    @Test
    public void given_country_with_no_continent_then_it_shows_dash() throws Exception {
        Country country = mock(Country.class);
        when(country.getContinent()).thenReturn(null);
        presenter.bind(country);
        verify(view).setContinent("-");
    }

    @Test
    public void given_country_with_population_greater_than_0_then_it_shows_population() throws Exception {
        Country country = mock(Country.class);
        when(country.getPopulation()).thenReturn("500");
        presenter.bind(country);
        verify(view).setPopulation("500");
    }

    @Test
    public void given_country_with_population_equal_or_lesser_than_0_then_it_shows_uninhabited() throws Exception {
        Country country = mock(Country.class);
        when(country.getPopulation()).thenReturn("0");
        presenter.bind(country);
        verify(view).setPopulation("uninhabited");
    }
}