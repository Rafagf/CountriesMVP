package com.rafagarcia.countries;

import com.rafagarcia.countries.di.components.DaggerApplicationComponent;
import com.rafagarcia.countries.di.modules.ApplicationModule;
import com.rafagarcia.countries.di.modules.NetworkModule;

import io.appflate.restmock.RESTMockServer;

public class TestApplication extends MyApplication {

    @Override
    protected void createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(RESTMockServer.getUrl()))
                .build();
    }
}