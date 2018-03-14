package com.rafagarcia.countries.main.countrieslist.holder;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;
import com.rafagarcia.countries.utilities.FormattingUtils;

/**
 * Created by Rafa on 09/03/2018.
 */

public class CountryListViewHolderPresenter {

    private ResourcesProvider resourcesProvider;
    private FlagProvider flagProvider;
    private CountryListViewHolderMvp.View view;

    public CountryListViewHolderPresenter(CountryListViewHolderMvp.View view, ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        this.view = view;
        this.resourcesProvider = resourcesProvider;
        this.flagProvider = flagProvider;
    }

    void bind(Country country) {
        setName(country);
        setContinent(country);
        setPopulation(country);
        setFlag(country);
    }

    private void setName(Country country) {
        view.setName(country.getName());
    }

    private void setContinent(Country country) {
        if (country.getContinent() == null || country.getContinent().isEmpty()) {
            view.setContinent(resourcesProvider.getText(R.string.continent, "-"));
        } else {
            view.setContinent(resourcesProvider.getText(R.string.continent, country.getContinent()));
        }
    }

    private void setPopulation(Country country) {
        if (country.getPopulation() == null || country.getPopulation().isEmpty()) {
            view.setPopulation(resourcesProvider.getText(R.string.population, "-"));
        } else {
            view.setPopulation(resourcesProvider.getText(R.string.population, FormattingUtils.formatPopulation(country.getPopulation())));
        }
    }

    private void setFlag(Country country) {
        view.setFlag(flagProvider.getFlagUrl(country.getAlpha2Code()));
    }
}
