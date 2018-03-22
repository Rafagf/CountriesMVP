package com.rafagarcia.countries.main.detailedview;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;
import com.rafagarcia.countries.utilities.FormattingUtils;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public class DetailedCountryPresenter {

    private DetailedCountryMvp.View view;
    private DetailedCountryMvp.Interactor interactor;
    private ResourcesProvider resourcesProvider;
    private FlagProvider flagProvider;
    private DetailedCountryViewModel countryViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public DetailedCountryPresenter(DetailedCountryMvp.View view, DetailedCountryMvp.Interactor interactor, ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        this.view = view;
        this.resourcesProvider = resourcesProvider;
        this.flagProvider = flagProvider;
        this.interactor = interactor;
    }

    void init(String countryName) {
        fetchCountry(countryName);
    }

    private void fetchCountry(String countryName) {
        interactor.getCountry(countryName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MaybeObserver<Country>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        compositeDisposable.add(disposable);
                    }

                    @Override
                    public void onSuccess(Country country) {
                        onFetchingCountrySucceed(country);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //todo fetch
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onFetchingCountrySucceed(Country country) {
        DetailedCountryViewModelMapper mapper = new DetailedCountryViewModelMapper();
        countryViewModel = mapper.mapFrom(country);

        setName();
        setFlag();
        setCapital();
        setContinent();
        setRegion();
        setMap();
        setPopulation();
        setArea();
        setDemonym();
        setNativeName();
        setBorderCountries();
    }

    void stop() {
        compositeDisposable.clear();
    }

    private void setName() {
        view.setName(countryViewModel.getName());
    }

    private void setFlag() {
        view.setFlag(flagProvider.getFlagUrl(countryViewModel.getAlpha2Code()));
    }

    private void setCapital() {
        if (countryViewModel.getCapital() == null || countryViewModel.getCapital().isEmpty()) {
            view.setCapital("-");
        } else {
            view.setCapital(countryViewModel.getCapital());
        }
    }

    private void setContinent() {
        if (countryViewModel.getContinent() == null || countryViewModel.getContinent().isEmpty()) {
            view.setContinent("-");
        } else {
            view.setContinent(countryViewModel.getContinent());
        }
    }

    private void setRegion() {
        if (countryViewModel.getRegion() == null || countryViewModel.getRegion().isEmpty()) {
            view.setRegion("-");
        } else {
            view.setRegion(countryViewModel.getRegion());
        }
    }

    private void setPopulation() {
        view.setPopulation(resourcesProvider.getText(R.string.population) + FormattingUtils.formatPopulation(countryViewModel.getPopulation()));
    }

    private void setArea() {
        view.setArea(resourcesProvider.getText(R.string.area) + FormattingUtils.formatArea(countryViewModel.getArea()));
    }

    private void setDemonym() {
        if (countryViewModel.getDemonym() == null || countryViewModel.getDemonym().isEmpty()) {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + "-");
        } else {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + countryViewModel.getDemonym());
        }
    }

    private void setNativeName() {
        if (countryViewModel.getNativeName() == null || countryViewModel.getNativeName().isEmpty()) {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + "-");
        } else {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + countryViewModel.getNativeName());
        }
    }

    private void setBorderCountries() {
        for (String countryName : countryViewModel.getBorderCountries()) {
            view.addBorderCountry(countryName);
        }
    }

    private void setMap() {
        view.addMapMarker(countryViewModel.getLatlng(), countryViewModel.getName());
    }
}
