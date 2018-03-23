package com.rafagarcia.countries.main.detailedview.borderview;

/**
 * Created by Rafa on 23/03/2018.
 */

public interface BordersViewMvp {
    interface View {
        void setBorderTitleVisibility(boolean visibility);
        void addBorder(String country);
    }
}
