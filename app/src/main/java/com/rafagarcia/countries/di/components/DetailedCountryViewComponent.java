package com.rafagarcia.countries.di.components;

import com.rafagarcia.countries.di.modules.DetailedCountryViewModule;
import com.rafagarcia.countries.di.scopes.PerActivity;
import com.rafagarcia.countries.main.detailedview.DetailedCountryActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {DetailedCountryViewModule.class}
)
public interface DetailedCountryViewComponent {
    void inject(DetailedCountryActivity detailedCountryActivity);
}