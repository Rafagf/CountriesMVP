package com.rafagarcia.countries.main.detailedview.borderview;

import java.util.List;

/**
 * Created by Rafa on 23/03/2018.
 */

public class BordersPresenter {

    private BordersViewMvp.View view;

    public BordersPresenter(BordersViewMvp.View view) {
        this.view = view;
    }

    void bind(List<String> countries) {
        if (countries.size() > 0) {
            setBorderTitleVisibility(true);
            setBorders(countries);
        } else {
            setBorderTitleVisibility(false);
        }
    }

    private void setBorderTitleVisibility(boolean visibility) {
        view.setBorderTitleVisibility(visibility);
    }


    private void setBorders(List<String> countries) {
        for (String country : countries) {
            view.addBorder(country);
        }
    }

    void onCountryClicked(String name) {
        view.goToCountryDetailedView(name);
    }
}
