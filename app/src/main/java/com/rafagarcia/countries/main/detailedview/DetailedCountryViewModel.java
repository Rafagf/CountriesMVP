package com.rafagarcia.countries.main.detailedview;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Rafa on 20/03/2018.
 */

public class DetailedCountryViewModel {

    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
    private String continent;
    private String region;
    private String area;
    private String nativeName;
    private String population;
    private String demonym;
    private LatLng latlng;
    private List<String> borderCountryAlphaList;

    public DetailedCountryViewModel(DetailedCountryViewModelBuilder builder) {
        name = builder.name;
        alpha2Code = builder.alpha2Code;
        alpha3Code = builder.alpha3Code;
        capital = builder.capital;
        continent = builder.continent;
        region = builder.region;
        area = builder.area;
        nativeName = builder.nativeName;
        population = builder.population;
        demonym = builder.demonym;
        latlng = builder.latlng;
        borderCountryAlphaList = builder.borderCountryAlphaList;
    }

    public String getName() {
        return name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public String getArea() {
        return area;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getPopulation() {
        return population;
    }

    public String getDemonym() {
        return demonym;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public List<String> getBorderCountryAlphaList() {
        return borderCountryAlphaList;
    }

    public static class DetailedCountryViewModelBuilder {
        private String name;
        private String alpha2Code;
        private String alpha3Code;
        private String capital;
        private String continent;
        private String region;
        private String area;
        private String nativeName;
        private String population;
        private String demonym;
        private LatLng latlng;
        private List<String> borderCountryAlphaList;

        public DetailedCountryViewModel build() {
            return new DetailedCountryViewModel(this);
        }

        public DetailedCountryViewModelBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DetailedCountryViewModelBuilder setAlpha2Code(String alpha2Code) {
            this.alpha2Code = alpha2Code;
            return this;
        }

        public DetailedCountryViewModelBuilder setAlpha3Code(String alpha3Code) {
            this.alpha3Code = alpha3Code;
            return this;
        }

        public DetailedCountryViewModelBuilder setCapital(String capital) {
            this.capital = capital;
            return this;
        }

        public DetailedCountryViewModelBuilder setContinent(String continent) {
            this.continent = continent;
            return this;
        }

        public DetailedCountryViewModelBuilder setRegion(String region) {
            this.region = region;
            return this;
        }


        public DetailedCountryViewModelBuilder setArea(String area) {
            this.area = area;
            return this;
        }

        public DetailedCountryViewModelBuilder setNativeName(String nativeName) {
            this.nativeName = nativeName;
            return this;
        }

        public DetailedCountryViewModelBuilder setPopulation(String population) {
            this.population = population;
            return this;
        }

        public DetailedCountryViewModelBuilder setDemonym(String demonym) {
            this.demonym = demonym;
            return this;
        }

        public DetailedCountryViewModelBuilder setLatLng(LatLng latLng) {
            this.latlng = latLng;
            return this;
        }

        public DetailedCountryViewModelBuilder setBorderCountryAlphaList(List<String> borderCountryAlphaList) {
            this.borderCountryAlphaList = borderCountryAlphaList;
            return this;
        }
    }
}
