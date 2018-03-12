package com.rafagarcia.countries.di.providers;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by Rafa on 12/03/2018.
 */

public class TextProvider {

    private Context context;

    public TextProvider(Context context) {
        this.context = context;
    }

    public String getText(@StringRes int resId){
        return context.getResources().getText(resId).toString();
    }
}
