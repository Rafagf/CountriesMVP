package com.rafagarcia.countries.main.countrieslist.holder;

/**
 * Created by Rafa on 12/03/2018.
 */

public interface CountryListViewHolderMvp {

    interface View {

        void setName(String name);

        void setRegion(String name);

        void setPopulation(String name);

        void setFlag(String url);
    }
}
