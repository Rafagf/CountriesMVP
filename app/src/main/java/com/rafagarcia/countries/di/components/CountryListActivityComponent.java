package com.rafagarcia.countries.di.components;

import com.rafagarcia.countries.di.modules.CountryListActivityModule;
import com.rafagarcia.countries.di.scopes.PerActivity;
import com.rafagarcia.countries.main.countrieslist.CountryListActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {CountryListActivityModule.class}
)
public interface CountryListActivityComponent {
    void inject(CountryListActivity mainActivity);
}