package com.rafagarcia.countries.main.repositories;

import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountriesMemoryDataSource {

    private List<Country> countries = new ArrayList<>();

    public Maybe<List<Country>> getCountries() {
        if (countries != null && countries.size() > 0) {
            List<Country> shallowCopy = new ArrayList<>(countries);
            return Maybe.just(shallowCopy);
        } else {
            return Maybe.empty();
        }
    }

    public void save(List<Country> countries) {
        this.countries.addAll(countries);
    }

    public void clear() {
        countries.clear();
    }

    public Maybe<Country> getCountryByName(String name) {
        for (Country country : countries) {
            if (country.getName().equals(name)) {
                return Maybe.just(country);
            }
        }

        return Maybe.empty();
    }

    public Maybe<Country> getCountryByAlpha3(String alpha) {
        for (Country country : countries) {
            if (country.getAlpha3Code().equals(alpha)) {
                return Maybe.just(country);
            }
        }

        return Maybe.empty();
    }
}
