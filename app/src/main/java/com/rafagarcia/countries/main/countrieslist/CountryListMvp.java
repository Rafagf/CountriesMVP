package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Rafa on 12/03/2018.
 */

public interface CountryListMvp {
    interface View {
        void updateList(List<Country> countries);
        void goToCountryDetailedView(Country country);
        void showError();
    }

    interface Interactor {
        Maybe<List<Country>> getCountries();
    }
}
