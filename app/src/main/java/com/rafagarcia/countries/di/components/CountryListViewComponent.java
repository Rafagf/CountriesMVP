package com.rafagarcia.countries.di.components;

import com.rafagarcia.countries.di.modules.CountryListViewModule;
import com.rafagarcia.countries.di.scopes.PerActivity;
import com.rafagarcia.countries.main.countrieslist.CountryListActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {CountryListViewModule.class}
)
public interface CountryListViewComponent {
    void inject(CountryListActivity mainActivity);
}