package com.rafagarcia.countries.main.country;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountryFragment extends Fragment implements CountryFragmentInterface{

    @Bind(R.id.nameTextView) TextView nameTextView;
    @Bind(R.id.flagImageView) ImageView flagImageView;
    @Bind(R.id.regionTextView) TextView regionTextView;
    @Bind(R.id.subregionTextView) TextView subregionTextView;
    @Bind(R.id.populationTextView) TextView populationTextView;
    @Bind(R.id.areaTextView) TextView areaTextView;
    @Bind(R.id.denonymTextView) TextView denonymTextView;
    @Bind(R.id.nativeTextView) TextView nativeNameTextView;
    @Bind(R.id.capitalTextView) TextView capitalTextView;
    @Bind(R.id.bordersLinearLayout) LinearLayout bordersLinearLayout;
    @Bind(R.id.borderCountriesTextView) TextView borderCountriesTextView;

    private String countryName;
    private CountryPresenter presenter;
    private Country country;
    private static final String COUNTRY_NAME = "country_name";
    private OnFragmentInteractionListener mListener;

    public static CountryFragment newInstance(String countryName) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putString(COUNTRY_NAME, countryName);
        fragment.setArguments(args);
        return fragment;
    }

    public CountryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryName = getArguments().getString(COUNTRY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);
        init();
        initViews(view);
        return view;
    }

    private void init() {
        presenter = new CountryPresenter(this);
        country = presenter.getCountryByName(countryName);
    }

    private void initViews(View view) {
        ButterKnife.bind(this, view);
        Picasso.with(getContext())
                .load(country.getFlagUrl())
                .placeholder(R.drawable.interrogation)
                .into(flagImageView);

        Resources resources = getResources();
        nameTextView.setText(country.getName());
        regionTextView.setText(country.getRegion());
        subregionTextView.setText(country.getSubregion());
        capitalTextView.setText(resources.getString(R.string.capital) + country.getCapital());
        populationTextView.setText(resources.getString(R.string.population) + country.getPopulation());
        areaTextView.setText(resources.getString(R.string.area) + country.getArea());
        denonymTextView.setText(resources.getString(R.string.demonym) + country.getDemonym());
        nativeNameTextView.setText(resources.getString(R.string.native_name) + country.getNativeName());
        borderCountriesTextView.setText(resources.getString(R.string.border_countries));
        displayBorders();
    }

    private void displayBorders() {

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
    public Country getCountry() {
        return country;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
