package com.rafagarcia.countries;

import android.app.Application;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class MyApplication extends Application{

    private static MyApplication singleton;

    public static MyApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
