package com.rafagarcia.countries.main.countrieslist.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by Rafa on 09/03/2018.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.flagImageView) ImageView flagImageView;
    @Bind(R.id.nameTextView) TextView nameTextView;
    @Bind(R.id.regionTextView) TextView regionTextView;
    @Bind(R.id.populationTextView) TextView populationTextView;

    private CountryViewHolderPresenter presenter;

    public CountryViewHolder(View itemView) {
        super(itemView);
        presenter = new CountryViewHolderPresenter(this);
    }

    public void bind(Country country) {
        presenter.bind(country);
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setRegion(String name) {
        regionTextView.setText(name);
    }

    public void setPopulation(String name) {
        nameTextView.setText(name);
    }

    public void setFlag(String url) {
        Picasso.with(itemView.getContext())
                .load(url)
                .into(flagImageView);
    }
}
