package com.rafagarcia.countries.di.components;

import com.rafagarcia.countries.di.modules.BordersViewModule;
import com.rafagarcia.countries.di.scopes.PerCustomView;
import com.rafagarcia.countries.main.detailedview.borderview.BordersView;

import dagger.Component;

@PerCustomView
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {BordersViewModule.class}
)
public interface BordersViewComponent {
    void inject(BordersView bordersView);
}