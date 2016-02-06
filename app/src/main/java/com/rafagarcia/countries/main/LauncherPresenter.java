package com.rafagarcia.countries.main;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherPresenter {
    LauncherActivityInterface view;
    LauncherInteractor interactor;

    public LauncherPresenter(LauncherActivityInterface view, LauncherInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchCountriesInfo() {
        interactor.fetchCountriesInfo(this);
    }
}
