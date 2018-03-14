package com.rafagarcia.countries.main.detailedview;

import android.content.Intent;
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

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.CountryListActivity;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountryFragment extends Fragment implements CountryFragmentInterface {

    @Bind(R.id.nameTextView) TextView mNameTextView;
    @Bind(R.id.flagImageView) ImageView mFlagImageView;
    @Bind(R.id.continentTextView) TextView mRegionTextView;
    @Bind(R.id.subregionTextView) TextView mSubregionTextView;
    @Bind(R.id.populationTextView) TextView mPopulationTextView;
    @Bind(R.id.areaTextView) TextView mAreaTextView;
    @Bind(R.id.denonymTextView) TextView mDenonymTextView;
    @Bind(R.id.nativeTextView) TextView mNativeNameTextView;
    @Bind(R.id.capitalTextView) TextView mCapitalTextView;
    @Bind(R.id.bordersLinearLayout) LinearLayout mBordersLinearLayout;
    @Bind(R.id.borderCountriesTextView) TextView mBorderCountriesTextView;
    private static final String COUNTRY_TAG = "mCountry";
    private static final String COUNTRY = "country_name";
    private Country mCountry;
    private CountryPresenter mPresenter;

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
            mCountry = getArguments().getParcelable(COUNTRY);
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
        mPresenter = new CountryPresenter(this);
        mPresenter.showCountryInformation(mCountry);
    }

    private void initViews(View view) {
        ButterKnife.bind(this, view);
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.mapFragment, MapFragment.newInstance(mCountry)).commit();
    }

    @Override
    public void showFlag(String flagUrl) {
        Picasso.with(getContext())
                .load(mCountry.getFlagUrl())
                .into(mFlagImageView);
    }

    @Override
    public void showName(String name) {
        mNameTextView.setText(mCountry.getName());
    }

    @Override
    public void showRegion(String region) {
        mRegionTextView.setText(mCountry.getContinent());
    }

    @Override
    public void showSubregion(String subregion) {
        mSubregionTextView.setText(mCountry.getRegion());
    }

    @Override
    public void showCapital(String capital) {
        mCapitalTextView.setText(getResources().getString(R.string.capital) + mCountry.getCapital());
    }

    @Override
    public void showPopulation(String population) {
        mPopulationTextView.setText(getResources().getString(R.string.population) + mCountry.getPopulation());
    }

    @Override
    public void showArea(String area) {
        mAreaTextView.setText(getResources().getString(R.string.area) + mCountry.getArea());
    }

    @Override
    public void showDenonym(String denonym) {
        mDenonymTextView.setText(getResources().getString(R.string.demonym) + mCountry.getDemonym());
    }

    @Override
    public void showNativeName(String nativeName) {
        mNativeNameTextView.setText(getResources().getString(R.string.native_name) + mCountry.getNativeName());
    }

    @Override
    public void showBorderCountry(final Country country) {
        TextView textView = new TextView(getContext());
        textView.setBackgroundResource(R.drawable.circle_shape);
        textView.setTag(country.getName());
        textView.setTextSize(14);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        textView.setText(country.getName());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 5, 20, 5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 20, 5, 10);
        textView.setLayoutParams(layoutParams);
        mBordersLinearLayout.addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CountryActivity.class);
                intent.putExtra(CountryListActivity.COUNTRY, country);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showBorderCountriesText() {
        mBorderCountriesTextView.setText(getString(R.string.border_countries));
    }

    public void hideBorderCountriesText(){
        mBorderCountriesTextView.setVisibility(View.GONE);
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
//            if(resultCode != ConnectionResult.SUCCESS) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage("Google Play Services not available");
//                builder.setCancelable(true);
//                builder.setPositiveButton("OK", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            } else {
//                googleMap = (((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(););
//                initMap();
//            }

            return view;
        }

        private void initMap() {
//            if(country != null) {
//                LatLng latLng = new LatLng(country.getLatlng().latitude, country.getLatlng().longitude);
//                googleMap.addMarker(new MarkerOptions().position(latLng).title(country.getName()));
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            }
        }
    }
}
