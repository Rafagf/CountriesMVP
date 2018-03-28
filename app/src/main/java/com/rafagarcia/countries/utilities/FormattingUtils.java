package com.rafagarcia.countries.utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import io.reactivex.annotations.NonNull;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class FormattingUtils {

    public static String formatPopulation(@NonNull String population) {
        int populationNumber = Integer.valueOf(population);
        if (populationNumber <= 0) {
            return "uninhabited";
        } else if (populationNumber <= 9999) {
            return population;
        } else if (populationNumber > 9999 && populationNumber <= 999999) {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(populationNumber / 1000f) + "K";
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(populationNumber / 1000000f) + "M";
        }
    }

    public static String formatArea(@NonNull String area) {
        float areaNumber = Float.valueOf(area);
        if (areaNumber <= 0) {
            return 0 + " m²";
        } else if (areaNumber <= 999) {
            return area + " m²";
        } else if (areaNumber > 999 && areaNumber <= 999999) {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(areaNumber / 1000f) + " km²";
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            return decimalFormat.format(areaNumber / 1000000f) + "M km²";
        }
    }
}

