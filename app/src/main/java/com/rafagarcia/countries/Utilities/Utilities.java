package com.rafagarcia.countries.Utilities;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class Utilities {

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
