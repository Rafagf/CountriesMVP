package com.rafagarcia.countries.main.detailedview.borderview;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Rafa on 23/03/2018.
 */
public class BordersPresenterTest {

    private BordersViewMvp.View view;
    private BordersPresenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(BordersViewMvp.View.class);
        presenter = new BordersPresenter(view);
    }

    @Test
    public void given_number_of_borders_is_greater_than_zero_when_started_then_show_borders() throws Exception {
        List<String> countries = new ArrayList<>();
        countries.add("Portugal");
        countries.add("Spain");

        presenter.bind(countries);

        verify(view).setBorderTitleVisibility(true);
        verify(view).addBorder("Portugal");
        verify(view).addBorder("Spain");
        verifyNoMoreInteractions(view);
    }

    @Test
    public void given_number_of_borders_is_zero_when_started_then_hide_everything() throws Exception {
        List<String> countries = new ArrayList<>();

        presenter.bind(countries);

        verify(view).setBorderTitleVisibility(false);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onCountryClicked() throws Exception {
        presenter.onCountryClicked("Portugal");

        verify(view).goToCountryDetailedView("Portugal");
        verifyNoMoreInteractions(view);
    }
}