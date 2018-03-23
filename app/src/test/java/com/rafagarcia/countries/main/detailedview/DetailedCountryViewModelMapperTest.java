package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 22/03/2018.
 */
public class DetailedCountryViewModelMapperTest {

    DetailedCountryViewModelMapper mapper;

    @Before
    public void setUp() {
        mapper = new DetailedCountryViewModelMapper();
    }

    @Test
    public void given_country_then_view_model_fields_are_correct() throws Exception {
        Country country = getMockedCountry();
        DetailedCountryViewModel viewModel = mapper.mapFrom(country);
        assertEquals(viewModel.getName(), "Spain");
        assertEquals(viewModel.getAlpha2Code(), "ES");
        assertEquals(viewModel.getAlpha3Code(), "ESP");
        assertEquals(viewModel.getCapital(), "Madrid");
        assertEquals(viewModel.getContinent(), "Europe");
        assertEquals(viewModel.getRegion(), "Southern Europe");
        assertEquals(viewModel.getArea(), "505992.0");
        assertEquals(viewModel.getPopulation(), "46439864");
        assertEquals(viewModel.getDemonym(), "Spanish");
        assertEquals(viewModel.getNativeName(), "España");
        assertEquals(viewModel.getLatlng().latitude, 40.0, 0);
        assertEquals(viewModel.getLatlng().longitude, -4.0, 0);
        assertThat(viewModel.getBorderCountryAlphaList(), contains("AND", "FRA", "GIB", "PRT", "MAR"));
    }

    private Country getMockedCountry() {
        Country country = mock(Country.class);
        when(country.getName()).thenReturn("Spain");
        when(country.getAlpha2Code()).thenReturn("ES");
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