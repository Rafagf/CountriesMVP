package com.rafagarcia.countries.main.countrieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.countrieslist.holder.CountryListViewHolder;

import java.util.List;

/**
 * Created by Rafael Garcia on 12/10/15.
 */
public class CountryListAdapter extends RecyclerView.Adapter {

    interface CountriesAdapterInteraction {
        void onCountrySelected(CountryListViewModel country);
    }

    private List<CountryListViewModel> countries;
    private CountriesAdapterInteraction listener;

    public CountryListAdapter(List<CountryListViewModel> countries, CountriesAdapterInteraction listener) {
        this.countries = countries;
        this.listener = listener;
    }

    @Override
    public CountryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_card, parent, false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CountryListViewHolder countryHolder = (CountryListViewHolder) holder;
        countryHolder.bind(countries.get(position));
        countryHolder.itemView.setOnClickListener(view -> listener.onCountrySelected(countries.get(position)));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}
