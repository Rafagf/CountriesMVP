package com.rafagarcia.countries.countrieslist;

import android.app.Activity;
import android.content.Intent;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountriesListFragment extends Fragment implements CountriesListFragmentInterface {

    RecyclerView recyclerView;
    private CountriesListPresenter presenter;
    private CountriesListInteractor interactor;
    private OnFragmentInteractionListener mListener;
    private CountriesAdapter adapter;
    private List<Country> countryList;

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
        if (getArguments() != null) {
        }
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

    //todo add butterknife
    private void initViews(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        countryList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new CountriesAdapter(countryList, getContext(), countriesAdapterListener);
        recyclerView.setAdapter(adapter);
        presenter.loadCountries();
    }

    CountriesAdapterInterface countriesAdapterListener = new CountriesAdapterInterface() {
        @Override
        public void countrySelected(String name) {
           presenter.countrySelected(name);
        }
    };

    private void init() {
        interactor = new CountriesListInteractor();
        presenter = new CountriesListPresenter(this, interactor);
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
        return countryList;
    }

    @Override
    public void updateAdapter(List<Country> countries) {
        countryList.addAll(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void goToSelectedCountry(String name){
        mListener.goToSelectedCountry(name);
    }

    public interface OnFragmentInteractionListener {
        void goToSelectedCountry(String name);
    }
}
