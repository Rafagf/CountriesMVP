package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountriesListPresenter {

    private CountriesListActivity view;
    private CountriesListInteractor interactor;
    private List<Country> countryList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    CountriesListPresenter(CountriesListActivity view, CountriesListInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void init() {
        interactor.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Country>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(List<Country> countries) {
                        onFetchingCountriesSucceed(countries);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFetchingCountriesFailed();
                    }
                });
    }

    private void onFetchingCountriesFailed() {
        //todo error
    }

    private void onFetchingCountriesSucceed(List<Country> countries) {
        countryList.clear();
        countryList.addAll(countries);
        view.updateAdapter(countries);
    }

    void onQueryTextSubmit(String query) {
        search(query);
    }

    void onQueryTextChange(String newText) {
        search(newText);
    }

    private void search(String query) {
        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : countryList) {
            if (country.getName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredCountries.add(country);
            }
        }

        view.updateAdapter(filteredCountries);
    }

    void onCountrySelected(String name) {

    }
}
