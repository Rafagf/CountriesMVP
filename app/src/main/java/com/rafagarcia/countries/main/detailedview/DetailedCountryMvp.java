package com.rafagarcia.countries.main.detailedview;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.model.Country;

import java.util.List;

import io.reactivex.Single;

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

        void addMapMarker(LatLng latLng, String country);

        void setBorders(List<String> borderCountries);

        void setBordersVisibility(boolean visibility);

        void showError();
    }

    interface Interactor {
        Single<Country> getCountry(String name);
        Single<List<String>> getBorderCountriesName(List<String> alphaCountryList);
    }
}