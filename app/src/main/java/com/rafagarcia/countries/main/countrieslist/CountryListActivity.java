package com.rafagarcia.countries.main.countrieslist;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerCountryListActivityComponent;
import com.rafagarcia.countries.di.modules.CountryListActivityModule;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryListActivity extends AppCompatActivity implements CountryListMvp.View {

    public static final String COUNTRY = "country";

    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.search_view)
    MaterialSearchView searchView;
    @Bind(R.id.countries_list_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.go_to_top_button)
    FloatingActionButton goToTopButton;

    @Inject
    CountryListPresenter presenter;

    private CountryListAdapter adapter;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void initViews() {
        ButterKnife.bind(this);
        setToolbar();
        setSearchView();
        setList();
    }

    private void setToolbar() {
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
                int color = ContextCompat.getColor(CountryListActivity.this, R.color.plain_grey);
                window.setStatusBarColor(color);
            }

            @Override
            public void onSearchViewClosed() {
                int color = ContextCompat.getColor(CountryListActivity.this, R.color.colorPrimary);
                window.setStatusBarColor(color);
            }
        });
    }

    private void setList() {
        recyclerView = findViewById(R.id.countries_list_recycler_view);
        countryList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new CountryListAdapter(countryList, country -> presenter.onCountrySelected(country));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                presenter.onListScrolled(manager.findFirstVisibleItemPosition());
            }
        });
    }

    private void init() {
        ApplicationComponent applicationComponent = ((MyApplication) getApplication()).getApplicationComponent();
        DaggerCountryListActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .countryListActivityModule(new CountryListActivityModule(this))
                .build().
                inject(this);

        presenter.init();
    }

    @Override
    public void updateList(List<Country> countries) {
        countryList.clear();
        countryList.addAll(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void goToCountryDetailedView(Country country) {
        Toast.makeText(this, "Go to " + country.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Snackbar.make(findViewById(android.R.id.content), R.string.there_was_an_error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, view -> presenter.onRetryClicked())
                .show();
    }

    @Override
    public void setGoToTopButtonVisibility(boolean visibility) {
        goToTopButton.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.go_to_top_button)
    public void onGoToTopClicked() {
        recyclerView.scrollToPosition(0);
        appBarLayout.setExpanded(true);
    }
}
