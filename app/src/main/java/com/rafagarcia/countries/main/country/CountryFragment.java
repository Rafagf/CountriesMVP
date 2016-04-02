package com.rafagarcia.countries.main.country;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
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
import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.CountriesListActivity;
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
    private static final String COUNTRY_TAG = "country";
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

    private void initViews(View view) {
        ButterKnife.bind(this, view);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.mapFragment, MapFragment.newInstance(country)).commit();
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
    public void showFlag(String flagUrl) {
        Picasso.with(getContext())
                .load(country.getFlagUrl())
                .placeholder(R.drawable.interrogation)
                .into(flagImageView);
    }

    @Override
    public void showName(String name) {
        nameTextView.setText(country.getName());
    }

    @Override
    public void showRegion(String region) {
        regionTextView.setText(country.getRegion());
    }

    @Override
    public void showSubregion(String subregion) {
        subregionTextView.setText(country.getSubregion());
    }

    @Override
    public void showCapital(String capital) {
        capitalTextView.setText(getResources().getString(R.string.capital) + country.getCapital());
    }

    @Override
    public void showPopulation(String population) {
        populationTextView.setText(getResources().getString(R.string.population) + country.getPopulation());
    }

    @Override
    public void showArea(String area) {
        areaTextView.setText(getResources().getString(R.string.area) + country.getArea());
    }

    @Override
    public void showDenonym(String denonym) {
        denonymTextView.setText(getResources().getString(R.string.demonym) + country.getDemonym());
    }

    @Override
    public void showNativeName(String nativeName) {
        nativeNameTextView.setText(getResources().getString(R.string.native_name) + country.getNativeName());
    }

    @Override
    public void showBorderCountry(final Country country) {
        TextView textView = new TextView(getContext());
        textView.setBackgroundResource(R.drawable.circle_shape);
        textView.setTag(country.getName());
        textView.setTextSize(14);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        textView.setText(country.getName());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 5, 20, 5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 20, 5, 10);
        textView.setLayoutParams(layoutParams);
        bordersLinearLayout.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CountryActivity.class);
                intent.putExtra(CountriesListActivity.COUNTRY, country);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showBorderCountriesText() {
        borderCountriesTextView.setText(getString(R.string.border_countries));
    }

    public void hideBorderCountriesText(){
        borderCountriesTextView.setVisibility(View.GONE);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static class MapFragment extends Fragment {
        GoogleMap googleMap;
        Country country;

        public static MapFragment newInstance(Country country) {
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            args.putParcelable(COUNTRY_TAG, country);
            fragment.setArguments(args);
            return fragment;
        }

        public MapFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            country = getArguments().getParcelable(COUNTRY_TAG);
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
            if(country != null) {
                LatLng latLng = new LatLng(country.getLatlng().latitude, country.getLatlng().longitude);
                googleMap.addMarker(new MarkerOptions().position(latLng).title(country.getName()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }
}
