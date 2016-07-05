package com.rafagarcia.countries;

import android.app.Application;
import android.content.res.Configuration;

import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class MyApplication extends Application{

    private static MyApplication singleton;
    private List<Country> countries;
    private Languages localisedLanguage = Languages.EN;

    public enum Languages {
        EN, ES, FR, JA, IT, DE;
    }

    public static MyApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        setLocalisedLanguage();
    }

    /**
     * Method to set the app language according to the device language
     */
    private void setLocalisedLanguage(){
        String deviceLanguage = Locale.getDefault().getLanguage().toUpperCase();
        //todo support different languages
        localisedLanguage = Languages.EN;
    }

    /**
     * Setter for countries field, that also sort the countries by localised name
     * @param countries list of countries
     */
    public void loadCountries(List<Country> countries){
        this.countries = countries;
        Collections.sort(this.countries);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Languages getLocalisedLanguage() {
        return localisedLanguage;
    }

    /**
     * Gets the country by alphaCode
     * @param alphaCode country alphaCode
     * @return country or null if it doesn't exist
     */
    public Country getCountryByAlphaCode(String alphaCode){
        for(int i = 0; i < countries.size(); i++){
            if(countries.get(i).getAlpha3Code().equals(alphaCode)){
                return countries.get(i);
            }
        }
        return null;
    }

    /**
     * Gets the country position into the Countries array by country name
     * @param name country name
     * @return -1 if country doesn't exist, otherwise the country index
     */
    public int getCountryPosByName(String name){
        for (int i = 0; i < countries.size(); i++) {
            if(countries.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public Country getCountryByName(String name){
        for (int i = 0; i < countries.size(); i++) {
            if(countries.get(i).getName().equals(name)){
                return countries.get(i);
            }
        }
        return null;
    }

    /**
     * Gets a list of countries filtering by region
     * @param region the region
     * @return list of countries in the given region
     */
    public List<Country> getCountriesInRegion(String region){
        List<Country> regionCountries = new ArrayList<>();
        for (Country country : this.countries) {
            if(country.getRegion().equals(region)){
                regionCountries.add(country);
            }
        }
        return regionCountries;
    }

    /**
     * Gets a list of countries filtering by subregion
     * @param subregion the subregion
     * @return list of countries in the given subregion
     */
    public List<Country> getCountriesInSubRegion(String subregion){
        List<Country> subRegionCountries = new ArrayList<>();
        for (Country country : this.countries) {
            if(country.getSubregion().equals(subregion)){
                subRegionCountries.add(country);
            }
        }
        return subRegionCountries;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
