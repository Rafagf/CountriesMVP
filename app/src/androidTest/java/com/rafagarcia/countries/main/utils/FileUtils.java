package com.rafagarcia.countries.main.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;

import io.reactivex.annotations.NonNull;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by Rafa on 15/03/2018.
 */

public class FileUtils {

    @Nullable
    public static String getJsonFromAsset(@NonNull String path) {
        try {
            InputStream is = getInstrumentation().getContext().getResources().getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
