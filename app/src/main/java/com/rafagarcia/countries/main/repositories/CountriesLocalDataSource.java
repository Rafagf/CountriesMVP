package com.rafagarcia.countries.main.repositories;

import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafagarcia.countries.model.Country;

import java.io.IOException;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Rafa on 08/03/2018.
 */

/**
 * This data sources saves data in shared preferences as a json to just make it easier.
 * In a real app, it would use a proper local storage (Room, Realm or any other)
 */
public class CountriesLocalDataSource {

    private static String COUNTRIES_JSON = "countries_json";
    private SharedPreferences sharedPreferences;

    public CountriesLocalDataSource(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Maybe<List<Country>> getCountries() {
        String countriesInJson = getCountriesJsonFromSharedPreferences();
        if (countriesInJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Country> countries = mapper.readValue(countriesInJson, new TypeReference<List<Country>>(){});
                return Maybe.just(countries);
            } catch (IOException e) {
                e.printStackTrace();
                return Maybe.empty();
            }
        }

        return Maybe.empty();
    }

    public void save(List<Country> countries) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String countriesInJson = mapper.writeValueAsString(countries);
            saveCountriesJsonInSharedPreferences(countriesInJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        sharedPreferences.edit().remove(COUNTRIES_JSON).apply();
    }

    private void saveCountriesJsonInSharedPreferences(String json){
        sharedPreferences.edit().putString(COUNTRIES_JSON, json).apply();
    }

    private String getCountriesJsonFromSharedPreferences() {
        return sharedPreferences.getString(COUNTRIES_JSON, null);
    }
}
