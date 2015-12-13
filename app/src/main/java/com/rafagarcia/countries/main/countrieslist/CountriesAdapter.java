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

    private List<Country> countries;
    private Context context;
    private static final String COUNTRY_POSITION = "country_position";
    CountriesAdapterInterface listener;

    public CountriesAdapter(List<Country> countries, Context context, CountriesAdapterInterface listener ) {
        this.countries = countries;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.country_card, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {

        final Country country = countries.get(position);
        holder.countryName.setText(country.getName());
        holder.countryPopulation.setText(context.getResources().getString(R.string.population) + country.getPopulation());
        holder.countryRegion.setText(context.getResources().getString(R.string.region) + country.getRegion());

        Picasso.with(context)
                .load(country.getFlagUrl())
                .placeholder(R.drawable.interrogation)
                .into(holder.countryFlag);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.countrySelected(country.getName());
//                int pos = ((MyApplication)context.getApplicationContext())
//                        .getCountryPosByName(country.getName());
//
//                if(pos >= 0) {
//                    Intent intent = new Intent(context, CountryActivity.class);
//                    intent.putExtra(COUNTRY_POSITION, pos);
//                    context.startActivity(intent);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder{

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
