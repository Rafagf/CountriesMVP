package com.rafagarcia.countries.main.detailedview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerDetailedCountryViewComponent;
import com.rafagarcia.countries.di.modules.DetailedCountryViewModule;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailedCountryActivity extends AppCompatActivity implements DetailedCountryMvp.View {

    public static final String COUNTRY_TAG = "country";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.flag_image_view)
    ImageView flagImageView;
    @Bind(R.id.continent_text_view)
    TextView continentTextView;
    @Bind(R.id.region_text_view)
    TextView regionTextView;
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

    @Inject
    DetailedCountryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Intent intent = getIntent();
        Country country = intent.getParcelableExtra(COUNTRY_TAG);
        initViews();
        init(country);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    private void initViews() {
        ButterKnife.bind(this);
        setToolbar();
    }

    private void init(Country country) {
        ApplicationComponent applicationComponent = ((MyApplication) getApplicationContext()).getApplicationComponent();
        DaggerDetailedCountryViewComponent.builder()
                .applicationComponent(applicationComponent)
                .detailedCountryViewModule(new DetailedCountryViewModule(this, country))
                .build()
                .inject(this);

        presenter.init();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
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
}
