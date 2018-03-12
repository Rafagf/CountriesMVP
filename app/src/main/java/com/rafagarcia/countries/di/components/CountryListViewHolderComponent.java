package com.rafagarcia.countries.di.components;

import com.rafagarcia.countries.di.modules.CountryListViewHolderModule;
import com.rafagarcia.countries.di.scopes.PerViewHolder;
import com.rafagarcia.countries.main.countrieslist.holder.CountryListViewHolder;

import dagger.Component;

@PerViewHolder
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {CountryListViewHolderModule.class}
)
public interface CountryListViewHolderComponent {
    void inject(CountryListViewHolder holder);
}