package com.rafagarcia.countries.main.detailedview;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.RxImmediateSchedulerRule;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 22/03/2018.
 */
public class DetailedCountryPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();


    private DetailedCountryMvp.View view;
    private DetailedCountryMvp.Interactor interactor;
    private DetailedCountryPresenter presenter;
    private ResourcesProvider resourcesProvider;
    private FlagProvider flagProvider;

    @Before
    public void setUp() throws Exception {
        view = mock(DetailedCountryMvp.View.class);
        interactor = mock(DetailedCountryInteractor.class);
        resourcesProvider = mock(ResourcesProvider.class);
        flagProvider = mock(FlagProvider.class);
        presenter = new DetailedCountryPresenter(view, interactor, resourcesProvider, flagProvider);
    }

    @Test
    public void given_country_when_it_starts_then_fetch_country() throws Exception {
        when(interactor.getCountry("Spain")).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_name() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setName("Spain");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_flag() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(flagProvider.getFlagUrl("ES")).thenReturn("url");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setFlag("url");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_capital() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setCapital("Madrid");
    }

    @Test
    public void given_country_successfully_fetched_and_country_does_not_have_a_capital_when_started_then_set_capital_to_nothing() {
        Country country = getMockedCountry();
        when(country.getCapital()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setCapital("-");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_continent() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setContinent("Europe");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_continent_when_started_then_set_continent_to_nothing() {
        Country country = getMockedCountry();
        when(country.getContinent()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setContinent("-");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_region() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setRegion("Southern Europe");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_region_when_started_then_set_region_to_nothing() {
        Country country = getMockedCountry();
        when(country.getRegion()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setRegion("-");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_map() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).addMapMarker(new LatLng(40.0, -4.0), "Spain");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_population() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.population)).thenReturn("Population: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setPopulation("Population: 46.4M");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_population_when_started_then_set_population_to_nothing() {
        Country country = getMockedCountry();
        when(country.getPopulation()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.population)).thenReturn("Population: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setPopulation("Population: ");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_area() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.area)).thenReturn("Area: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setArea("Area: 505.9 km²");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_area_when_started_then_set_area_to_nothing() {
        Country country = getMockedCountry();
        when(country.getArea()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.area)).thenReturn("Area: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setArea("Area: ");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_demonym() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.demonym)).thenReturn("Demonym: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setDemonym("Demonym: Spanish");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_demonym_when_started_then_set_demonym_to_nothing() {
        Country country = getMockedCountry();
        when(country.getDemonym()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.demonym)).thenReturn("Demonym: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setDemonym("Demonym: -");
    }

    @Test
    public void given_country_successfully_fetched_when_started_then_set_native_name() {
        Country country = getMockedCountry();
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setNativeName("Native name: España");
    }

    @Test
    public void given_country_successfully_fetched_and_it_does_not_have_native_name_when_started_then_set_native_name_to_nothing() {
        Country country = getMockedCountry();
        when(country.getNativeName()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(anyList())).thenReturn(Single.never());
        when(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(view).setNativeName("Native name: -");
    }

    @Test
    public void given_borders_successfully_fetched_when_started_then_show_borders() {
        Country country = getMockedCountry();
        when(country.getNativeName()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        List<String> borderCountries = new ArrayList<>();
        borderCountries.add("Portugal");
        borderCountries.add("Marocco");
        borderCountries.add("Andorra");
        borderCountries.add("Gibraltar");
        borderCountries.add("France");
        when(interactor.getBorderCountriesName(country.getBorderCountryAlphaList())).thenReturn(Single.just(borderCountries));
        when(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(interactor).getBorderCountriesName(country.getBorderCountryAlphaList());
        verify(view).setBordersVisibility(true);
        verify(view).setBorders(borderCountries);
    }

    @Test
    public void given_fetching_borders_errored_when_started_then_hide_borders() {
        Country country = getMockedCountry();
        when(country.getNativeName()).thenReturn(null);
        when(interactor.getCountry("Spain")).thenReturn(Single.just(country));
        when(interactor.getBorderCountriesName(country.getBorderCountryAlphaList())).thenReturn(Single.error(new Throwable()));
        when(resourcesProvider.getText(R.string.native_name)).thenReturn("Native name: ");

        presenter.init("Spain");

        verify(interactor).getCountry("Spain");
        verify(interactor).getBorderCountriesName(country.getBorderCountryAlphaList());
        verify(view).setBordersVisibility(false);
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

    @Test
    public void when_retry_button_is_clicked_then_fetch_country() {
        when(interactor.getCountry("Spain")).thenReturn(Single.never());

        presenter.onRetryClicked("Spain");

        verify(interactor).getCountry("Spain");
        verifyNoMoreInteractions(view, interactor);
    }
}