package com.rafagarcia.countries.main.launcher;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.main.launcher.LauncherActivityInterface;
import com.rafagarcia.countries.main.launcher.LauncherInteractor;
import com.rafagarcia.countries.model.Country;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

/**
 * Created by rafagarcia on 06/02/2016.
 */
public class LauncherPresenter {
    LauncherActivityInterface view;
    LauncherInteractor interactor;

    public LauncherPresenter(LauncherActivityInterface view, LauncherInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void fetchCountriesInfo(boolean networkAvailable, String jsonFromCache) {
        if(jsonFromCache != null){
            loadCountriesInApp(parseCountriesJson(jsonFromCache));
            view.goToMainScreen();
        }
        else{
            if(networkAvailable){
                interactor.fetchCountriesInfo(this);
            }
            else{
                onErrorFetchingCountries();
            }
        }
    }

    public void countriesFetchedSuccessfully(Response<ResponseBody> response) {
        try {
            loadCountriesInApp(parseCountriesJson(response.body().string()));
            view.goToMainScreen();
        } catch (IOException e) {
            view.showError();
        }
    }

    public void onErrorFetchingCountries() {
        view.showError();
    }

    public void loadCountriesInApp(List<Country> countries){
        MyApplication.getInstance().loadCountries(countries);
    }


    private List<Country> parseCountriesJson(String jsonString) {
        List<Country> countryList = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String englishName = jsonObject.getString("name");
                    JSONObject translations = jsonObject.getJSONObject("translations");
                    String frenchName = translations.getString("fr");
                    String spanishName = translations.getString("es");
                    String germanName = translations.getString("de");
                    String japaneseName = translations.getString("ja");
                    String italianName = translations.getString("it");
                    String name = getLocalisedName(englishName, frenchName, spanishName,
                            germanName, japaneseName, italianName);
                    String nativeName = jsonObject.getString("nativeName");
                    String alpha2Code = jsonObject.getString("alpha2Code");
                    String alpha3Code = jsonObject.getString("alpha3Code");
                    String region = jsonObject.getString("region");
                    String subregion = jsonObject.getString("subregion");
                    String capital = jsonObject.getString("capital");
                    String population = jsonObject.getString("population");
                    String area = jsonObject.getString("area");
                    String denonym = jsonObject.getString("demonym");
                    JSONArray latLngArray = jsonObject.getJSONArray("latlng");
                    LatLng latLng = new LatLng(latLngArray.getDouble(0), latLngArray.getDouble(1));
                    JSONArray bordersArray = jsonObject.getJSONArray("borders");

                    List<String> borders = new ArrayList<>();
                    for(int j = 0; j < bordersArray.length(); j++){
                        borders.add(bordersArray.getString(j));
                    }

                    countryList.add(new Country(name, englishName, frenchName, spanishName, germanName,
                            japaneseName, italianName, nativeName, alpha2Code, alpha3Code,
                            region, subregion, capital, population, area, denonym, latLng, borders));
                }

                catch (Exception e){
                    Log.e("Error", "Error parsing particular country: " + e);
                }
            }
        }

        catch (Exception e){
            Log.e("Error", "Error parsing countries json: " + e);
        }

        return countryList;
    }

    private String getLocalisedName(String englishName, String frenchName, String spanishName,
                                    String germanName, String japaneseName, String italianName) {
        String localisedName = englishName;
        MyApplication.Languages localisedLanguage =
                MyApplication.getInstance().getLocalisedLanguage();

        switch (localisedLanguage) {
            case EN:
                localisedName = englishName;
                break;
            case ES:
                localisedName = spanishName;
                break;
            case FR:
                localisedName = frenchName;
                break;
            case JA:
                localisedName = japaneseName;
                break;
            case IT:
                localisedName = italianName;
                break;
            case DE:
                localisedName = germanName;
                break;
        }

        return localisedName;
    }
}
