package com.rafagarcia.countries.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.Utilities.Utilities;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;
import com.rafagarcia.countries.model.Country;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafagarcia on 29/11/2015.
 */
public class LauncherActivity extends Activity implements LauncherActivityInterface{

    GetCountriesAsyncTask workerThread;
    LauncherPresenter presenter;
    LauncherInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
    }

    private void init() {
        interactor = new LauncherInteractor();
        presenter = new LauncherPresenter(this, interactor);
        fetchCountriesInfo();
    }

    @Override
    public void fetchCountriesInfo() {
        presenter.fetchCountriesInfo();
    }

    public void loadCountriesInformation(){
        if(Utilities.isNetworkAvailable(getApplicationContext())){
            workerThread = new GetCountriesAsyncTask(this);
            workerThread.execute();
        }
        else{
            String countriesJson = Utilities.getCountriesJsonFromSharedPreferences(getApplicationContext());
            if(countriesJson != null){
                Toast.makeText(getApplicationContext(), "From cache", Toast.LENGTH_LONG).show();
                ((MyApplication)getApplicationContext()).loadCountries(parseCountriesJson(countriesJson));
                Intent intent = new Intent(LauncherActivity.this, CountriesListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
//                Snackbar.make(this.findViewById(R.id.recyclerView), "Loading from cache",
//                        Snackbar.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
//                Snackbar.make(this.findViewById(R.id.recyclerView), "There is not an internet connection",
//                        Snackbar.LENGTH_LONG).show();
            }
        }
    }

    class GetCountriesAsyncTask extends AsyncTask<Void, Void, String> {
        Activity mActivity;
        String response;

        GetCountriesAsyncTask(Activity activity) {
            mActivity = activity;
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url;
            int responseCode = -1;
            try {
                url = new URL("https://restcountries.eu/rest/v1/all");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer responseStringBuffer = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseStringBuffer.append(inputLine);
                }

                in.close();
                response = responseStringBuffer.toString();
                responseCode = conn.getResponseCode();

            } catch (Exception e) {
                Log.e("Error", "Error calling countries API: " + e);
            }

            return String.valueOf(responseCode);
        }

        @Override
        protected void onPostExecute(String code) {
            if(mActivity != null) {
                if (code.startsWith("20")) {
                    Utilities.saveCountriesJsonInSharedPreferences(response, getApplicationContext());
                            ((MyApplication) getApplicationContext()).loadCountries(parseCountriesJson(response));
                    Intent intent = new Intent(LauncherActivity.this, CountriesListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "no internet connection", Toast.LENGTH_LONG).show();
//                    Snackbar.make(mActivity.findViewById(R.id.recyclerView), "Sorry, there is an internet connection problem",
//                            Snackbar.LENGTH_LONG).show();
                }
            }
        }
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
                ((MyApplication) getApplicationContext()).getLocalisedLanguage();

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
