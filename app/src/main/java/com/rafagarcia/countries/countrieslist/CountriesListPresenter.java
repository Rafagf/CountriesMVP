package com.rafagarcia.countries.countrieslist;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    CountriesListInteractor interactor;
    CountriesListFragmentInterface view;

    public CountriesListPresenter(CountriesListFragmentInterface view, CountriesListInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void loadCountries(){
        interactor.loadCountries(this);
    }
}
