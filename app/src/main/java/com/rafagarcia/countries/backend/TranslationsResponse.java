package com.rafagarcia.countries.backend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafagarcia on 28/02/2016.
 */
public class TranslationsResponse implements Parcelable {

    @SerializedName("de")
    private String mGerman;

    @SerializedName("es")
    private String mSpanish;

    @SerializedName("fr")
    private String mFrench;

    @SerializedName("ja")
    private String mJapanese;

    @SerializedName("it")
    private String mItalian;

    public String getGerman() {
        return mGerman;
    }

    public String getSpanish() {
        return mSpanish;
    }

    public String getFrench() {
        return mFrench;
    }

    public String getJapanese() {
        return mJapanese;
    }

    public String getItalian() {
        return mItalian;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mGerman);
        dest.writeString(this.mSpanish);
        dest.writeString(this.mFrench);
        dest.writeString(this.mJapanese);
        dest.writeString(this.mItalian);
    }

    public TranslationsResponse() {
    }

    protected TranslationsResponse(Parcel in) {
        this.mGerman = in.readString();
        this.mSpanish = in.readString();
        this.mFrench = in.readString();
        this.mJapanese = in.readString();
        this.mItalian = in.readString();
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
