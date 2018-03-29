package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class CountryListPresenter {

    private CountryListMvp.View view;
    private CountryListMvp.Interactor interactor;
    private List<Country> countryList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CountryListPresenter(CountryListMvp.View view, CountryListMvp.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    void init() {
        fetchCountries();
    }

    void stop() {
        compositeDisposable.clear();
    }

    void onRetryClicked() {
        fetchCountries();
    }

    private void fetchCountries() {
        interactor.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<List<Country>>() {
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

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onFetchingCountriesFailed() {
        view.showError();
    }

    private void onFetchingCountriesSucceed(List<Country> countries) {
        countryList.clear();
        countryList.addAll(countries);
        view.updateList(countries);
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

        view.updateList(filteredCountries);
    }

    void onCountrySelected(Country country) {
        view.goToCountryDetailedView(country);
    }

    void onListScrolled(int firstVisibleItem) {
        view.setGoToTopButtonVisibility(firstVisibleItem != 0);
    }
}
