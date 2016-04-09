package com.rafagarcia.countries.main.countrieslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    private static final String COUNTRY_POSITION = "country_position";
    private List<Country> mCountries;
    private Context mContext;
    private CountriesAdapterInterface mListener;

    public CountriesAdapter(List<Country> countries, Context context, CountriesAdapterInterface mListener) {
        this.mCountries = countries;
        this.mContext = context;
        this.mListener = mListener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.country_card, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        final Country country = mCountries.get(position);
        holder.countryName.setText(country.getName());
        holder.countryPopulation.setText(mContext.getResources().getString(R.string.population) + country.getPopulation());
        holder.countryRegion.setText(mContext.getResources().getString(R.string.region) + country.getRegion());

        Picasso.with(mContext)
                .load(country.getFlagUrl())
                .placeholder(R.drawable.interrogation)
                .into(holder.countryFlag);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.countrySelected(country.getName(), holder.countryFlag, holder.countryName,
                        holder.countryRegion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        ImageView countryFlag;
        TextView countryName;
        TextView countryRegion;
        TextView countryPopulation;

        public CountryViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            countryFlag = (ImageView)itemView.findViewById(R.id.flagImageView);
            countryName = (TextView)itemView.findViewById(R.id.nameTextView);
            countryRegion = (TextView)itemView.findViewById(R.id.regionTextView);
            countryPopulation = (TextView)itemView.findViewById(R.id.populationTextView);
        }
    }
}
