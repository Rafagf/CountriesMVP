package com.rafagarcia.countries.backend;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 28/02/2016.
 */
public class CountryResponse implements Parcelable {

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
        dest.writeList(this.latlng);
        dest.writeStringList(this.borders);
        dest.writeParcelable(this.translations, flags);
    }

    public CountryResponse() {
    }

    protected CountryResponse(Parcel in) {
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
        this.latlng = new ArrayList<Double>();
        in.readList(this.latlng, List.class.getClassLoader());
        this.borders = in.createStringArrayList();
        this.translations = in.readParcelable(TranslationsResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<CountryResponse> CREATOR = new Parcelable.Creator<CountryResponse>() {
        public CountryResponse createFromParcel(Parcel source) {
            return new CountryResponse(source);
        }

        public CountryResponse[] newArray(int size) {
            return new CountryResponse[size];
        }
    };
}
