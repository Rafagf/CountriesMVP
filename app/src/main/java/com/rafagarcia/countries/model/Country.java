package com.rafagarcia.countries.model;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.backend.CountryResponse;
import com.rafagarcia.countries.backend.TranslationsResponse;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class Country implements Comparable<Country>{

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
    private String flagUrl;
    private LatLng latlng;
    private List<String> borders;
    private Translations translations;

    public Country(CountryResponse response){
        this.name = response.getName();
        this.nativeName = response.getNativeName();
        this.alpha2Code = response.getAlpha2Code();
        this.alpha3Code = response.getAlpha3Code();
        this.region = response.getRegion();
        this.subregion = response.getSubregion();
        this.capital = response.getCapital();
        this.population = response.getPopulation();
        this.area = response.getArea();
        this.demonym = response.getDemonym();
        this.borders = response.getBorders();
        this.flagUrl = "http://www.geonames.org/flags/x/" + alpha2Code.toLowerCase() + ".gif";

        if(response.getLatlng() != null && response.getLatlng().size() == 2) {
            this.latlng = new LatLng(response.getLatlng().get(0), response.getLatlng().get(1));
        }

        this.translations = new Translations(response.getTranslations());
    }

    public Country(String name, String nativeName,
                   String alpha2Code, String alpha3Code, String region, String subregion, String capital,
                   String population, String area, String demonym) {
        this.name = name;
        this.nativeName = nativeName;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.region = region;
        this.subregion = subregion;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.demonym = demonym;
        this.flagUrl = "http://www.geonames.org/flags/x/" + alpha2Code.toLowerCase() + ".gif";
    }

    @Override
    public int compareTo(Country another) {
        return name.compareTo(another.name);
    }


    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getCapital() {
        return capital;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getArea() {
        return area;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public List<String> getBorders() {
        return borders;
    }

    private static class Translations{

        private String german;
        private String spanish;
        private String french;
        private String japanese;
        private String italian;

        public Translations(TranslationsResponse translationsResponse){
            this.german = translationsResponse.getGerman();
            this.spanish = translationsResponse.getSpanish();
            this.french = translationsResponse.getFrench();
            this.japanese = translationsResponse.getJapanese();
            this.italian = translationsResponse.getItalian();
        }

        public Translations(String german, String spanish, String french, String japanese, String italian){
            this.german = german;
            this.spanish = spanish;
            this.french = french;
            this.japanese = japanese;
            this.italian = italian;
        }
    }
}
