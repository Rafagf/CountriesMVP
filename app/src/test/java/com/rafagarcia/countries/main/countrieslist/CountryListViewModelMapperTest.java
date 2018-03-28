package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 22/03/2018.
 */
public class CountryListViewModelMapperTest {

    private CountryListViewModelMapper mapper;

    @Before
    public void setUp() {
        mapper = new CountryListViewModelMapper();
    }

    @Test
    public void given_country_then_view_model_fields_are_correct() throws Exception {
        Country country = getMockedCountry();
        CountryListViewModel viewModel = mapper.mapFrom(country);
        assertEquals(viewModel.getName(), "Spain");
        assertEquals(viewModel.getAlpha2Code(), "ES");
        assertEquals(viewModel.getContinent(), "Europe");
        assertEquals(viewModel.getPopulation(), "46439864");
    }

    private Country getMockedCountry() {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        when(country.getAlpha2Code()).thenReturn("ES");
        when(country.getAlpha3Code()).thenReturn("ESP");
        when(country.getCapital()).thenReturn("Madrid");
        when(country.getContinent()).thenReturn("Europe");
        when(country.getRegion()).thenReturn("Southern Europe");
        List<Double> latLng = new ArrayList<>();
        latLng.add(40.0);
        latLng.add(-4.0);
        when(country.getLatlng()).thenReturn(latLng);
        when(country.getDemonym()).thenReturn("Spanish");
        when(country.getArea()).thenReturn("505992.0");
        when(country.getPopulation()).thenReturn("46439864");
        when(country.getNativeName()).thenReturn("España");
        List<String> borders = new ArrayList<>();
        borders.add("AND");
        borders.add("FRA");
        borders.add("GIB");
        borders.add("PRT");
        borders.add("MAR");
        when(country.getBorderCountryAlphaList()).thenReturn(borders);

        return country;
    }

}