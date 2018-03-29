package com.rafagarcia.countries.di.providers;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Rafa on 12/03/2018.
 */

public class ResourcesProvider {

    private Context context;

    public ResourcesProvider(Context context) {
        this.context = context;
    }

    public String getText(@StringRes int resId){
        return context.getResources().getString(resId);
    }

    public String getText(@StringRes int resId, Object... args){
        return context.getResources().getString(resId, args);
    }
}
