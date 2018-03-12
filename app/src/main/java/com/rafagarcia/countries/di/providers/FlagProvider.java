package com.rafagarcia.countries.di.providers;

/**
 * Created by Rafa on 12/03/2018.
 */

public class FlagProvider {

    private final String FLAGS_URL = "http://www.geonames.org/flags/x/";

    public String getFlagUrl(String code) {
        return FLAGS_URL + code.toLowerCase() + ".gif";
    }
}
