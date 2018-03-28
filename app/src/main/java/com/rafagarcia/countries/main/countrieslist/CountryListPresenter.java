package com.rafagarcia.countries.main.countrieslist;

import com.rafagarcia.countries.R;
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
public class CountryListPresenter {

    private CountryListMvp.View view;
    private CountryListMvp.Interactor interactor;
    private List<CountryListViewModel> countryList = new ArrayList<>();
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
        view.showError();
    }

    private void onFetchingCountriesSucceed(List<Country> countries) {
        CountryListViewModelMapper mapper = new CountryListViewModelMapper();
        List<CountryListViewModel> countriesViewModel = mapper.mapFrom(countries);
        countryList.clear();
        countryList.addAll(countriesViewModel);
        view.updateList(countriesViewModel);
    }

    void onQueryTextSubmit(String query) {
        search(query);
    }

    void onQueryTextChange(String newText) {
        search(newText);
    }

    private void search(String query) {
        List<CountryListViewModel> filteredCountries = new ArrayList<>();
        for (CountryListViewModel country : countryList) {
            if (country.getName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredCountries.add(country);
            }
        }

        view.updateList(filteredCountries);
    }

    void onCountrySelected(CountryListViewModel country) {
        view.goToCountryDetailedView(country.getName());
    }

    void onListScrolled(int firstVisibleItem) {
        view.setGoToTopButtonVisibility(firstVisibleItem > 0);
    }

    public void onSearchViewShown() {
        view.setStatusBarColor(R.color.plain_grey);
    }

    public void onSearchViewClosed() {
        view.setStatusBarColor(R.color.color_primary);
    }
}
