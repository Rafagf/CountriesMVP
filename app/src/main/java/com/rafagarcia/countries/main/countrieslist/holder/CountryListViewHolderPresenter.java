package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.TextProvider;
import com.rafagarcia.countries.model.Country;

/**
 * Created by Rafa on 09/03/2018.
 */

public class CountryListViewHolderPresenter {

    private TextProvider textProvider;
    private FlagProvider flagProvider;
    private CountryListViewHolderMvp.View view;
    private Country country;

    public CountryListViewHolderPresenter(CountryListViewHolderMvp.View view, TextProvider textProvider, FlagProvider flagProvider) {
        this.view = view;
        this.textProvider = textProvider;
        this.flagProvider = flagProvider;
    }

    public void bind(Country country) {
        this.country = country;
        view.setName(country.getName());
        view.setRegion(textProvider.getText(R.string.region) + country.getRegion());
        view.setPopulation(textProvider.getText(R.string.population) + country.getPopulation());
        view.setFlag(flagProvider.getFlagUrl(country.getAlpha2Code()));
    }
}
