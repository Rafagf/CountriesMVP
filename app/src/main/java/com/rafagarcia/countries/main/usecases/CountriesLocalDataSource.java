package com.rafagarcia.countries.main.usecases;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafagarcia.countries.model.Country;

import java.io.IOException;
import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Rafa on 08/03/2018.
 */

public class CountriesLocalDataSource {

    private static String COUNTRIES_JSON = "countries_json";
    private Context context;

    public CountriesLocalDataSource(Context context) {
        this.context = context;
    }

    public Maybe<List<Country>> getCountries() {
        String countriesInJson = getCountriesJsonFromSharedPreferences(context);
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void saveCountriesJsonInSharedPreferences(String json){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(COUNTRIES_JSON, json).apply();
    }

    private String getCountriesJsonFromSharedPreferences(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(COUNTRIES_JSON, null);
    }
}
