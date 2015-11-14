package com.rafagarcia.countries.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by rafagarcia on 14/11/2015.
 */
public class Countries {
    private static Countries ourInstance;
    private List<Country> countries;
    private Languages localisedLanguage = Languages.EN;

    public enum Languages {
        EN, ES, FR, JA, IT, DE;
    }

    public synchronized static Countries getInstance() {
        if(ourInstance == null){
            ourInstance = new Countries();
        }
        return ourInstance;
    }

    private Countries() {
        setLocalisedLanguage();
    }

    /**
     * Method to set the app language according to the device language
     */
    private void setLocalisedLanguage(){
        String deviceLanguage = Locale.getDefault().getLanguage().toUpperCase();
        try {
            localisedLanguage = Languages.valueOf(deviceLanguage);
        }
        catch (Exception e){
            localisedLanguage = Languages.EN;
        }
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
     * Gets the country position into the Countries array by alphaCode
     * @param alphaCode country alphaCode
     * @return -1 if country doesn't exist, otherwise the country index
     */
    public int getCountryPositionByAlphaCode(String alphaCode){
        for(int i = 0; i < countries.size(); i++){
            if(countries.get(i).getAlpha3Code().equals(alphaCode)){
                return i;
            }
        }
        return -1;
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
}
