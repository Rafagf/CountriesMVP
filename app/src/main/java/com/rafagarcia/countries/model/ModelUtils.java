package com.rafagarcia.countries.model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by rafagarcia on 29/05/2016.
 */
public class ModelUtils {

    private static ModelUtils sModelUtils;
    private ObjectMapper mMapper;

    public static ModelUtils getInstance() {
        if (sModelUtils == null) {
            sModelUtils = new ModelUtils();
        }

        return sModelUtils;
    }

    public ObjectMapper getObjectMapper (){
        if (mMapper == null){
            mMapper = new ObjectMapper();
        }
        return mMapper;
    }
}
