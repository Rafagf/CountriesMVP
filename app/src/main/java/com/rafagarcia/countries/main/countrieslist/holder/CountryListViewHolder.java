package com.rafagarcia.countries.main.countrieslist.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafagarcia.countries.MyApplication;
import com.rafagarcia.countries.R;
import com.rafagarcia.countries.di.components.ApplicationComponent;
import com.rafagarcia.countries.di.components.DaggerCountryListViewHolderComponent;
import com.rafagarcia.countries.di.modules.CountryListViewHolderModule;
import com.rafagarcia.countries.model.Country;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rafa on 09/03/2018.
 */

public class CountryListViewHolder extends RecyclerView.ViewHolder implements CountryListViewHolderMvp.View {

    @Bind(R.id.flagImageView) ImageView flagImageView;
    @Bind(R.id.nameTextView) TextView nameTextView;
    @Bind(R.id.regionTextView) TextView regionTextView;
    @Bind(R.id.populationTextView) TextView populationTextView;

    @Inject
    CountryListViewHolderPresenter presenter;

    public CountryListViewHolder(View itemView) {
        super(itemView);
        ApplicationComponent applicationComponent = ((MyApplication) itemView.getContext().getApplicationContext()).getApplicationComponent();
        DaggerCountryListViewHolderComponent.builder()
                .applicationComponent(applicationComponent)
                .countryListViewHolderModule(new CountryListViewHolderModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Country country) {
        presenter.bind(country);
    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void setRegion(String name) {
        regionTextView.setText(name);
    }

    @Override
    public void setPopulation(String name) {
        populationTextView.setText(name);
    }

    @Override
    public void setFlag(String url) {
        Picasso.with(itemView.getContext())
                .load(url)
                .into(flagImageView);
    }
}
