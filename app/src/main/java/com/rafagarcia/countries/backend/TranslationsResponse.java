package com.rafagarcia.countries.backend;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafagarcia on 28/02/2016.
 */
public class TranslationsResponse {

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
}
