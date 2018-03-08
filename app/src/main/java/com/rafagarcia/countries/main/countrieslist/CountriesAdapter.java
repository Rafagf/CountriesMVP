package com.rafagarcia.countries.main.countrieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder> {

    interface CountriesAdapterInteraction {
        void onCountrySelected(String id);
    }

    private List<Country> countries;
    private CountriesAdapterInteraction listener;

    public CountriesAdapter(List<Country> countries, CountriesAdapterInteraction listener) {
        this.countries = countries;
        this.listener = listener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_card, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        final Country country = countries.get(position);
        holder.countryName.setText(country.getName());
        DecimalFormat formatter = new DecimalFormat("#,###");
        double populationDouble = Double.parseDouble(country.getPopulation());
        String populationFormatted = formatter.format(populationDouble);
        holder.countryPopulation.setText(holder.itemView.getContext().getResources().getString(R.string.population) + populationFormatted);
        holder.countryRegion.setText(holder.itemView.getContext().getResources().getString(R.string.region) + country.getRegion());

        Picasso.with(holder.itemView.getContext())
                .load(country.getFlagUrl())
                .into(holder.countryFlag);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCountrySelected(country.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
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
            countryFlag = itemView.findViewById(R.id.flagImageView);
            countryName = itemView.findViewById(R.id.nameTextView);
            countryRegion = itemView.findViewById(R.id.regionTextView);
            countryPopulation = itemView.findViewById(R.id.populationTextView);
        }
    }
}
