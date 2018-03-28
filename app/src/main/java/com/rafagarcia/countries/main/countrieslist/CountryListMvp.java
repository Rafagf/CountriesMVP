package com.rafagarcia.countries.main.countrieslist;

import android.support.annotation.ColorRes;

import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Rafa on 12/03/2018.
 */

public interface CountryListMvp {
    interface View {
        void setStatusBarColor(@ColorRes int color);

        void updateList(List<CountryListViewModel> countries);

        void goToCountryDetailedView(String countryName);

        void showError();

        void setGoToTopButtonVisibility(boolean visibility);
    }

    interface Interactor {
        Single<List<Country>> getCountries();
    }
}
