package com.rafagarcia.countries.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rafagarcia.countries.R;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class Utilities {

    private static String COUNTRIES_JSON = "countries_json";

    /**
     * Checks if there is any internet connection available
     * @param context currnt context
     * @return true is there is internet connection and false if there is not
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void saveCountriesJsonInSharedPreferences(String json, Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(COUNTRIES_JSON, json).apply();
    }

    public static String getCountriesJsonFromSharedPreferences(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(COUNTRIES_JSON, null);
    }

    public static void setToolbarTitle(Context context, Toolbar toolbar, String title) {
        TextView text = new TextView(context);
        text.setText(title);
        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M){
            text.setTextAppearance(android.R.style.TextAppearance_Material_Widget_ActionBar_Title_Inverse);
        } else{
            text.setTextAppearance(context, android.R.style.TextAppearance_Material_Widget_ActionBar_Title_Inverse);
        }
        toolbar.addView(text);
    }
}
