package com.rafagarcia.countries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 03/07/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Country extends Object implements Parcelable  {

    private String name;
    private String nativeName;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
    private String population;
    private String area;
    private String demonym;
    private String flagUrl;
    private List<String> borders;
    private List<Double> latlng;
    @JsonProperty("region")
    private String continent;
    @JsonProperty("subregion")
    private String region;

    public Country(){

    }

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

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
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

    public String getFlagUrl() {
        return flagUrl;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.nativeName);
        dest.writeString(this.alpha2Code);
        dest.writeString(this.alpha3Code);
        dest.writeString(this.continent);
        dest.writeString(this.region);
        dest.writeString(this.capital);
        dest.writeString(this.population);
        dest.writeString(this.area);
        dest.writeString(this.demonym);
        dest.writeString(this.flagUrl);
        dest.writeStringList(this.borders);
        dest.writeList(this.latlng);
    }

    protected Country(Parcel in) {
        this.name = in.readString();
        this.nativeName = in.readString();
        this.alpha2Code = in.readString();
        this.alpha3Code = in.readString();
        this.continent = in.readString();
        this.region = in.readString();
        this.capital = in.readString();
        this.population = in.readString();
        this.area = in.readString();
        this.demonym = in.readString();
        this.flagUrl = in.readString();
        this.borders = in.createStringArrayList();
        this.latlng = new ArrayList<Double>();
        in.readList(this.latlng, Double.class.getClassLoader());
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
