package com.rafagarcia.countries.main.detailedview;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.providers.FlagProvider;
import com.rafagarcia.countries.di.providers.ResourcesProvider;
import com.rafagarcia.countries.model.Country;
import com.rafagarcia.countries.utilities.FormattingUtils;

/**
 * Created by rafagarcia on 13/12/2015.
 */
public class DetailedCountryPresenter {

    private DetailedCountryMvp.View view;
    private Country country;
    private ResourcesProvider resourcesProvider;
    private FlagProvider flagProvider;

    public DetailedCountryPresenter(DetailedCountryMvp.View view, Country country, ResourcesProvider resourcesProvider, FlagProvider flagProvider) {
        this.view = view;
        this.country = country;
        this.resourcesProvider = resourcesProvider;
        this.flagProvider = flagProvider;
    }

    void init() {
        setName();
        setFlag();
        setCapital();
        setContinent();
        setRegion();
        setPopulation();
        setArea();
        setDemomym();
        setNativeName();
    }

    void stop() {

    }

    private void setName() {
        view.setName(country.getName());
    }

    private void setFlag() {
        view.setFlag(flagProvider.getFlagUrl(country.getAlpha2Code()));
    }

    private void setCapital() {
        if (country.getCapital() == null || country.getCapital().isEmpty()) {
            view.setCapital("-");
        } else {
            view.setCapital(country.getCapital());
        }
    }

    private void setContinent() {
        if (country.getContinent() == null || country.getContinent().isEmpty()) {
            view.setContinent("-");
        } else {
            view.setContinent(country.getContinent());
        }
    }

    private void setRegion() {
        if (country.getRegion() == null || country.getRegion().isEmpty()) {
            view.setRegion("-");
        } else {
            view.setRegion(country.getRegion());
        }
    }

    private void setPopulation() {
        view.setPopulation(resourcesProvider.getText(R.string.population) + FormattingUtils.formatPopulation(country.getPopulation()));
    }

    private void setArea() {
        view.setArea(resourcesProvider.getText(R.string.area) + FormattingUtils.formatArea(country.getArea()));
    }

    private void setDemomym() {
        if (country.getDemonym() == null || country.getDemonym().isEmpty()) {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + "-");
        } else {
            view.setDemonym(resourcesProvider.getText(R.string.demonym) + country.getDemonym());
        }
    }

    private void setNativeName() {
        if (country.getNativeName() == null || country.getNativeName().isEmpty()) {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + "-");
        } else {
            view.setNativeName(resourcesProvider.getText(R.string.native_name) + country.getNativeName());
        }
    }

    void onMapReady() {
        //todo screaming for a view model
        LatLng latLng = new LatLng(country.getLatlng().get(0), country.getLatlng().get(1));
        view.addMapMarker(latLng, country.getName());
    }
}
