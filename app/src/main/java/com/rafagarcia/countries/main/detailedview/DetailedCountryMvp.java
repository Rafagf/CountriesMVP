package com.rafagarcia.countries.main.detailedview;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public interface DetailedCountryMvp {

    interface View {
        void setFlag(String url);

        void setName(String name);

        void setContinent(String continent);

        void setRegion(String region);

        void setCapital(String capital);

        void setPopulation(String population);

        void setArea(String area);

        void setDemonym(String demonym);

        void setNativeName(String nativeName);
    }
}