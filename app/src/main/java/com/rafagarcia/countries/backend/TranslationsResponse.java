package com.rafagarcia.countries.backend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafagarcia on 28/02/2016.
 */
public class TranslationsResponse implements Parcelable {

    @SerializedName("de")
    private String german;

    @SerializedName("es")
    private String spanish;

    @SerializedName("fr")
    private String french;

    @SerializedName("ja")
    private String japanese;

    @SerializedName("it")
    private String italian;

    public String getGerman() {
        return german;
    }

    public String getSpanish() {
        return spanish;
    }

    public String getFrench() {
        return french;
    }

    public String getJapanese() {
        return japanese;
    }

    public String getItalian() {
        return italian;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.german);
        dest.writeString(this.spanish);
        dest.writeString(this.french);
        dest.writeString(this.japanese);
        dest.writeString(this.italian);
    }

    public TranslationsResponse() {
    }

    protected TranslationsResponse(Parcel in) {
        this.german = in.readString();
        this.spanish = in.readString();
        this.french = in.readString();
        this.japanese = in.readString();
        this.italian = in.readString();
    }

    public static final Parcelable.Creator<TranslationsResponse> CREATOR = new Parcelable.Creator<TranslationsResponse>() {
        public TranslationsResponse createFromParcel(Parcel source) {
            return new TranslationsResponse(source);
        }

        public TranslationsResponse[] newArray(int size) {
            return new TranslationsResponse[size];
        }
    };
}
