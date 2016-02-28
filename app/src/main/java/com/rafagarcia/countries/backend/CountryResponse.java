package com.rafagarcia.countries.backend;

import java.util.List;

/**
 * Created by rafagarcia on 28/02/2016.
 */
public class CountryResponse {

    private String name;
    private String nativeName;
    private String alpha2Code;
    private String alpha3Code;
    private String region;
    private String subregion;
    private String capital;
    private String population;
    private String area;
    private String demonym;
    private List<Double> latlng;
    private List<String> borders;
    private TranslationsResponse translations;

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    public String getDemonym() {
        return demonym;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public List<String> getBorders() {
        return borders;
    }

    public TranslationsResponse getTranslations() {
        return translations;
    }
}
