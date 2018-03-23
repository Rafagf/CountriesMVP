package com.rafagarcia.countries.di.modules;

import com.rafagarcia.countries.main.detailedview.borderview.BordersPresenter;
import com.rafagarcia.countries.main.detailedview.borderview.BordersViewMvp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rafa on 06/01/2017.
 */
@Module
public class BordersViewModule {

    private BordersViewMvp.View view;

    public BordersViewModule(BordersViewMvp.View view) {
        this.view = view;
    }

    @Provides
    public BordersPresenter providePresenter() {
        return new BordersPresenter(view);
    }
}
