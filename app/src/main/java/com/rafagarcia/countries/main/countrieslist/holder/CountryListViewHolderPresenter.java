package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;

/**
 * Created by Rafa on 09/03/2018.
 */

public class CountryListViewHolderPresenter {

    private ResourcesProvider resourcesProvider;
    private FlagProvider flagProvider;
    private CountryListViewHolderMvp.View view;
    private Country country;

    public CountryListViewHolderPresenter(CountryListViewHolderMvp.View view, ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        this.view = view;
        this.resourcesProvider = resourcesProvider;
        this.flagProvider = flagProvider;
    }

    public void bind(Country country) {
        this.country = country;
        view.setName(country.getName());
        view.setRegion(resourcesProvider.getText(R.string.region, country.getRegion()));
        view.setPopulation(resourcesProvider.getText(R.string.population, country.getPopulation()));
        view.setFlag(flagProvider.getFlagUrl(country.getAlpha2Code()));
    }
}
