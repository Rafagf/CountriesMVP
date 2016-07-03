package com.rafagarcia.countries.main.countrieslist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.country.CountryActivity;

public class CountriesListActivity extends AppCompatActivity implements CountriesListFragment.OnFragmentInteractionListener {

    public static final String COUNTRY = "country";
    private static final String COUNTRIES_LIST_FRAGMENT_TAG = "countries_list_fragment";
    private Toolbar mToolBar;
    private CountriesListFragment mFragment;
    private MaterialSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag(COUNTRIES_LIST_FRAGMENT_TAG) == null){
            mFragment = CountriesListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, mFragment).commit();
        } else{
            mFragment = (CountriesListFragment)fm.findFragmentByTag(COUNTRIES_LIST_FRAGMENT_TAG);
        }

        setToolbar();
        setSearchView();
    }

    private void setToolbar() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);
    }

    private void setSearchView() {
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mFragment.onQueryTextSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFragment.onQueryTextChange(newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            Window window = getWindow();

            @Override
            public void onSearchViewShown() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int color = ContextCompat.getColor(CountriesListActivity.this, R.color.plain_grey);
                    window.setStatusBarColor(color);
                }
            }

            @Override
            public void onSearchViewClosed() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int color = ContextCompat.getColor(CountriesListActivity.this, R.color.colorPrimary);
                    window.setStatusBarColor(color);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public void goToSelectedCountry(String name, View flagView, View nameView,
                                    View regionView) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra(COUNTRY, MyApplication.getInstance().getCountryByName(name));

        // Get the transition name from the string
        String flagTransition = getString(R.string.flag_transition);
        String countryTransition = getString(R.string.country_name_transition);
        String regionTransition = getString(R.string.region_transition);

        Pair<View, String> flag = Pair.create(flagView, flagTransition);
        Pair<View, String> country = Pair.create( nameView, countryTransition);
        Pair<View, String> region = Pair.create(regionView, regionTransition);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, flag, country, region);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}
