package com.rafagarcia.countries.main.launcher;

import com.rafagarcia.countries.backend.webapi.request.country.CountryWebApi;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherInteractor {

    CountryWebApi mCountryApi;

    public LauncherInteractor() {
        mCountryApi = new CountryWebApi();
    }

    void getAllCountries(Subscriber<List<Country>> subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    void getAllCountriesOneByOne(Subscriber subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.flatMap(new Func1<List<Country>, Observable<?>>() {
            @Override
            public Observable<?> call(List<Country> countries) {
                return Observable.from(countries);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    void getCountry(final String countryName, Subscriber subscriber){
        Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .map(new Func1<List<Country>, Object>() {
                    @Override
                    public Object call(List<Country> countries) {
                        for (Country country : countries) {
                            if(country.getName().equals(countryName)){
                                return country;
                            }
                        }
                        return null;
                    }
                })
                .subscribe(subscriber);
    }

    void getCountryFullName(final String countryName, Subscriber subscriber){
        Observable<List<Country>> country = mCountryApi.getCountryFullText(countryName);
        country.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .flatMap(new Func1<List<Country>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<Country> countries) {
                        return Observable.from(countries);
                    }
                })
                .subscribe(subscriber);
    }

    void getCountriesOneByOneByChainingApiCalls(Subscriber subscriber){
        final Observable<List<Country>> countries = mCountryApi.getCountries();
        countries.flatMap(new Func1<List<Country>, Observable<Country>>() {
            @Override
            public Observable<Country> call(List<Country> countries) {
                return Observable.from(countries);
            }
        })
                .flatMap(new Func1<Country, Observable<?>>() {
                    @Override
                    public Observable<List<Country>> call(Country country) {
                        return mCountryApi.getCountryFullText(country.getName());
                    }
                })
                .map(new Func1<Object, Country>() {
                    @Override
                    public Country call(Object o) {
                        if ((((List)o).size()) > 0) {
                            return (Country) ((List) o).get(0);
                        }
                        else {
                            return null;
                        }
                    }
                })
                .filter(new Func1<Country, Boolean>() {
                    @Override
                    public Boolean call(Country country) {
                        return country != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }
}
