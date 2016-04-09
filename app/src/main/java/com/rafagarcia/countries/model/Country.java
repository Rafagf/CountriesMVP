package com.rafagarcia.countries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.backend.CountryResponse;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class Country implements Comparable<Country>,Parcelable {

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
        dest.writeParcelable(this.latlng, 0);
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
        this.latlng = in.readParcelable(LatLng.class.getClassLoader());
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
