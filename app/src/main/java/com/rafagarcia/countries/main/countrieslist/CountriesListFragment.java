package com.rafagarcia.countries.main.countrieslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesListFragment extends Fragment implements CountriesListFragmentInterface {

    private RecyclerView mRecyclerView;
    private View mFlagToAnimate;
    private View mNameToAnimate;
    private View mRegionToAnimate;
    private CountriesListPresenter mPresenter;
    private OnFragmentInteractionListener mListener;
    private CountriesAdapter mAdapter;
    private List<Country> mCountryList;

    public static CountriesListFragment newInstance() {
        CountriesListFragment fragment = new CountriesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CountriesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countries_list, container, false);
        init();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mCountryList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new CountriesAdapter(mCountryList, getContext(), countriesAdapterListener);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadCountries();
    }

    CountriesAdapterInterface countriesAdapterListener = new CountriesAdapterInterface() {
        @Override
        public void countrySelected(String name, View flagView, View nameView,
                                    View regionView) {
            mFlagToAnimate = flagView;
            mNameToAnimate = nameView;
            mRegionToAnimate = regionView;
            mPresenter.countrySelected(name);
        }
    };

    private void init() {
        mPresenter = new CountriesListPresenter(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public List<Country> getCountryList() {
        return mCountryList;
    }

    @Override
    public void updateAdapter(List<Country> countries) {
        mCountryList.clear();
        mCountryList.addAll(countries);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToSelectedCountry(String name){
        mListener.goToSelectedCountry(name, mFlagToAnimate, mNameToAnimate, mRegionToAnimate);
    }

    public void onQueryTextSubmit(String query) {
        mPresenter.onQueryTextSubmit(query);
    }

    public void onQueryTextChange(String newText) {
        mPresenter.onQueryTextChange(newText);
    }

    public interface OnFragmentInteractionListener {
        void goToSelectedCountry(String name, View flagView, View nameView,
                                 View regionView);
    }
}
