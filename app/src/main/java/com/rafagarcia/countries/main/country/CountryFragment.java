package com.rafagarcia.countries.main.country;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CountryFragment extends Fragment implements CountryFragmentInterface {

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
    private Country country;
    private CountryPresenter presenter;
    private static final String COUNTRY = "country_name";
    private OnFragmentInteractionListener mListener;

    public static CountryFragment newInstance(Country country) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putParcelable(COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    public CountryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = getArguments().getParcelable(COUNTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, container, false);
        initViews(view);
        init();
        return view;
    }

    private void init() {
        presenter = new CountryPresenter(this);
        presenter.showCountryInformation(country);
    }

    public void showCountryInformation(Country country){
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

    private void initViews(View view) {
        ButterKnife.bind(this, view);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.mapFragment, MapFragment.newInstance()).commit();
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static class MapFragment extends Fragment {
        GoogleMap googleMap;
        Country country;

        public static MapFragment newInstance() {
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        public MapFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_map, container, false);
            int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
            if(resultCode != ConnectionResult.SUCCESS) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Google Play Services not available");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                googleMap = (((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap());
                initMap();
            }

            return view;
        }

        private void initMap() {
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
