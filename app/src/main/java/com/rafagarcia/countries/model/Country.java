package com.rafagarcia.countries.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class Country implements Comparable<Country>{

    private String name;
    private Translations translations;
    private String nativeName;
    private String alpha2Code;
    private String alpha3Code;
    private String region;
    private String subregion;
    private String capital;
    private String population;
    private String area;
    private String demonym;
    private LatLng latLng;
    private List<String> borders;
    private String flagUrl;

    public Country(String name, String englishName, String frenchName, String spanishName,
                   String germanName, String japaneseName, String italianName, String nativeName,
                   String alpha2Code, String alpha3Code, String region, String subregion, String capital,
                   String population, String area, String demonym, LatLng latLng, List<String> borders) {
        this.name = name;
        this.translations = new Translations(englishName, frenchName, spanishName, germanName,
                japaneseName, italianName);
        this.nativeName = nativeName;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.region = region;
        this.subregion = subregion;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.demonym = demonym;
        this.latLng = latLng;
        this.borders = borders;
        this.flagUrl = "http://www.geonames.org/flags/x/" + alpha2Code.toLowerCase() + ".gif";
    }

    @Override
    public int compareTo(Country another) {
        return name.compareTo(another.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    private class Translations{
        private String english;
        private String french;
        private String spanish;
        private String german;
        private String japanese;
        private String italian;

        public Translations(String english, String french, String spanish, String german, String japanese, String italian) {
            this.english = english;
            this.french = french;
            this.spanish = spanish;
            this.german = german;
            this.japanese = japanese;
            this.italian = italian;
        }

        public String getFrench() {
            return french;
        }

        public void setFrench(String french) {
            this.french = french;
        }

        public String getSpanish() {
            return spanish;
        }

        public void setSpanish(String spanish) {
            this.spanish = spanish;
        }

        public String getGerman() {
            return german;
        }

        public void setGerman(String german) {
            this.german = german;
        }

        public String getJapanese() {
            return japanese;
        }

        public void setJapanese(String japanese) {
            this.japanese = japanese;
        }

        public String getItalian() {
            return italian;
        }

        public void setItalian(String italian) {
            this.italian = italian;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }
    }
}
