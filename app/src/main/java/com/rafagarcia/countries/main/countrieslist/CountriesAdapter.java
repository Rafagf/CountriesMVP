package com.rafagarcia.countries.main.countrieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.holder.CountryViewHolder;
import com.rafagarcia.countries.model.Country;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class CountriesAdapter extends RecyclerView.Adapter {

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CountryViewHolder) holder).bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
