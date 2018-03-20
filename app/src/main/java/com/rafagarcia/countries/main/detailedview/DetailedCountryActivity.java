package com.rafagarcia.countries.main.detailedview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerDetailedCountryViewComponent;
import com.rafagarcia.countries.di.modules.DetailedCountryViewModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailedCountryActivity extends AppCompatActivity implements DetailedCountryMvp.View, OnMapReadyCallback {

    public static final String COUNTRY_NAME_TAG = "country_name";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.flag_image_view)
    ImageView flagImageView;
    @Bind(R.id.continent_text_view)
    TextView continentTextView;
    @Bind(R.id.region_text_view)
    TextView regionTextView;
    @Bind(R.id.map_view)
    MapView mapView;
    @Bind(R.id.population_text_view)
    TextView populationTextView;
    @Bind(R.id.area_text_view)
    TextView areaTextView;
    @Bind(R.id.demonym_text_view)
    TextView demonymTextView;
    @Bind(R.id.native_name_text_view)
    TextView nativeNameTextView;
    @Bind(R.id.capital_text_view)
    TextView capitalTextView;
    @Bind(R.id.borders_linear_layout)
    LinearLayout bordersLinearLayout;

    @Inject
    DetailedCountryPresenter presenter;

    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        String country = intent.getStringExtra(COUNTRY_NAME_TAG);
        initViews(savedInstanceState);
        init(country);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setToolbar();
        setMap(savedInstanceState);
    }

    private void setMap(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void init(String country) {
        ApplicationComponent applicationComponent = ((MyApplication) getApplicationContext()).getApplicationComponent();
        DaggerDetailedCountryViewComponent.builder()
                .applicationComponent(applicationComponent)
                .detailedCountryViewModule(new DetailedCountryViewModule(this))
                .build()
                .inject(this);

        presenter.init(country);
    }

    @Override
    public void setName(String name) {
        getSupportActionBar().setTitle(name);
    }

    @Override
    public void setFlag(String url) {
        Picasso.with(this)
                .load(url)
                .into(flagImageView);
    }

    @Override
    public void setContinent(String continent) {
        continentTextView.setText(continent);
    }

    @Override
    public void setRegion(String region) {
        regionTextView.setText(region);
    }

    @Override
    public void setCapital(String capital) {
        capitalTextView.setText(capital);
    }

    @Override
    public void setPopulation(String population) {
        populationTextView.setText(population);
    }

    @Override
    public void setArea(String area) {
        areaTextView.setText(area);
    }

    @Override
    public void setDemonym(String demonym) {
        demonymTextView.setText(demonym);
    }

    @Override
    public void setNativeName(String nativeName) {
        nativeNameTextView.setText(nativeName);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setAllGesturesEnabled(false);
    }

    @Override
    public void addMapMarker(LatLng latLng, String country) {
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(country));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3f));
    }

    @Override
    public void addBorderCountry(final String name) {
        //todo the root needs to be a parent
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.circle_shape);
        textView.setTextSize(14);
        textView.setTextColor(ContextCompat.getColor(this, R.color.color_primary));
        textView.setText(name);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 5, 20, 5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 20, 5, 10);
        textView.setLayoutParams(layoutParams);
        bordersLinearLayout.addView(textView);
    }
}
