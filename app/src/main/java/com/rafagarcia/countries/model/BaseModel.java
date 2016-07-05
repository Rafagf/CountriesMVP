package com.rafagarcia.countries.model;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;

/**
 * Created by rafagarcia on 29/05/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseModel extends Object {

    @Override
    public String toString() {
        try {
            return ModelUtils.getInstance().getObjectMapper().writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(getClass().getSimpleName(), "Error while trying to deserialize the model");
        }

        return super.toString();
    }
}
