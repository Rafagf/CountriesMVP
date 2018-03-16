package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.RxImmediateSchedulerRule;
import com.rafagarcia.countries.model.Country;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Rafa on 14/03/2018.
 */
public class CountryListPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private CountryListMvp.View view;
    private CountryListMvp.Interactor interactor;
    private CountryListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        interactor = mock(CountryListMvp.Interactor.class);
        view = mock(CountryListMvp.View.class);
        presenter = new CountryListPresenter(view, interactor);
    }

    @Test
    public void when_it_starts_then_it_get_countries() throws Exception {
        when(interactor.getCountries()).thenReturn(Maybe.never());

        presenter.init();

        verify(interactor).getCountries();
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void given_countries_successfully_fetched_when_started_then_it_updates_view() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country country1 = mock(Country.class);
        Country country2 = mock(Country.class);
        countries.add(country1);
        countries.add(country2);
        when(interactor.getCountries()).thenReturn(Maybe.just(countries));

        presenter.init();

        verify(interactor).getCountries();
        verify(view).updateList(countries);
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void given_countries_errored_when_started_then_it_shows_error() throws Exception {
        when(interactor.getCountries()).thenReturn(Maybe.error(new Throwable()));

        presenter.init();

        verify(interactor).getCountries();
        verify(view).showError();
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void when_search_text_is_changed() throws Exception {
        presenter.onQueryTextChange("query");

        verify(view).updateList(anyList());
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void when_search_text_is_submitted() throws Exception {
        presenter.onQueryTextSubmit("query");

        verify(view).updateList(anyList());
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void when_country_is_selected_then_go_to_detailed_view() throws Exception {
        Country country = mock(Country.class);
        presenter.onCountrySelected(country);

        verify(view).goToCountryDetailedView(country);
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void given_first_country_is_visible_when_user_scrolled_then_hide_go_to_top_button() {
        presenter.onListScrolled(0);

        verify(view).setGoToTopButtonVisibility(false);
        verifyNoMoreInteractions(view, interactor);
    }

    @Test
    public void given_first_country_is_visible_when_user_scrolled_then_show_go_to_top_button() {
        presenter.onListScrolled(0);

        verify(view).setGoToTopButtonVisibility(false);
        verifyNoMoreInteractions(view, interactor);
    }
}