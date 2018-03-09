package com.rafagarcia.countries.main.countrieslist;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.api.request.country.CountryApi;
import com.rafagarcia.countries.main.usecases.CountriesLocalDataSource;
import com.rafagarcia.countries.main.usecases.CountriesMemoryDataSource;
import com.rafagarcia.countries.main.usecases.CountriesRemoteDataSource;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesListActivity extends AppCompatActivity {

    public static final String COUNTRY = "country";
    private MaterialSearchView searchView;
    private CountriesAdapter adapter;
    private List<Country> countryList;
    private CountriesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void initViews() {
        setToolbar();
        setSearchView();
        setList();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void setSearchView() {
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onQueryTextSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.onQueryTextChange(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            Window window = getWindow();

            @Override
            public void onSearchViewShown() {
                int color = ContextCompat.getColor(CountriesListActivity.this, R.color.plain_grey);
                window.setStatusBarColor(color);
            }

            @Override
            public void onSearchViewClosed() {
                int color = ContextCompat.getColor(CountriesListActivity.this, R.color.colorPrimary);
                window.setStatusBarColor(color);
            }
        });
    }

    private void setList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        countryList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new CountriesAdapter(countryList, new CountriesAdapter.CountriesAdapterInteraction() {
            @Override
            public void onCountrySelected(String id) {
                presenter.onCountrySelected(id);
            }
        });

        recyclerView.setAdapter(adapter);
    }


    private void init() {
        //todo daggered
        presenter = new CountriesListPresenter(this, new CountriesListInteractor(new CountriesLocalDataSource(this), new CountriesRemoteDataSource(new CountryApi()), new CountriesMemoryDataSource()));
        presenter.init();
    }

    public void updateAdapter(List<Country> countries) {
        countryList.clear();
        countryList.addAll(countries);
        adapter.notifyDataSetChanged();
    }
}
