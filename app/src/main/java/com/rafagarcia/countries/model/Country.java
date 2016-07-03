package com.rafagarcia.countries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by rafagarcia on 03/07/2016.
 */
public class Country extends BaseModel implements Comparable<Country>, Parcelable  {

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

    private List<String> borders;
    private List<Double> latlng;

    public Country(){

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
        return "http://www.geonames.org/flags/x/" + alpha2Code.toLowerCase() + ".gif";
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

    public List<String> getBorders() {
        return borders;
    }

    public LatLng getLatlng() {
        return new LatLng(latlng.get(0), latlng.get(1));
    }

    @Override
    public int compareTo(Country another) {
        return name.compareTo(another.name);
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
        dest.writeString(this.region);
        dest.writeString(this.subregion);
        dest.writeString(this.capital);
        dest.writeString(this.population);
        dest.writeString(this.area);
        dest.writeString(this.demonym);
        dest.writeString(this.flagUrl);
        dest.writeStringList(this.borders);
    }

    protected Country(Parcel in) {
        this.name = in.readString();
        this.nativeName = in.readString();
        this.alpha2Code = in.readString();
        this.alpha3Code = in.readString();
        this.region = in.readString();
        this.subregion = in.readString();
        this.capital = in.readString();
        this.population = in.readString();
        this.area = in.readString();
        this.demonym = in.readString();
        this.flagUrl = in.readString();
        this.borders = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
