package com.rafagarcia.countries.main.detailedview.borderview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafagarcia.countries.R;
import com.rafagarcia.countries.main.detailedview.DetailedCountryActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rafa on 23/03/2018.
 */

public class BordersView extends LinearLayout implements BordersViewMvp.View {

    @Bind(R.id.borders_text_view)
    TextView borderTextView;
    @Bind(R.id.borders_linear_layout)
    LinearLayout bordersLinearLayout;

    @Inject
    BordersPresenter presenter;

    Context context;

    public BordersView(Context context) {
        super(context);
        init(context);
        initView();
    }

    public BordersView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initView();
    }

    public BordersView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initView();
    }

    public BordersView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initView();
    }

    public void init(Context context) {
        this.context = context;
        presenter = new BordersPresenter(this);
    }

    public void initView() {
        View view = View.inflate(context, R.layout.borders_view, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        addView(view);
        ButterKnife.bind(this);
    }

    public void bind(List<String> countries) {
        presenter.bind(countries);
    }

    @Override
    public void setBorderTitleVisibility(boolean visibility) {
        borderTextView.setVisibility(visibility ? VISIBLE : GONE);
    }

    @Override
    public void addBorder(String country) {
        TextView border = (TextView) View.inflate(context, R.layout.border_view, null);
        border.setText(country);
        bordersLinearLayout.addView(border);
        border.setOnClickListener(v -> presenter.onCountryClicked(country));
    }

    @Override
    public void goToCountryDetailedView(String countryName) {
        Intent intent = new Intent(context, DetailedCountryActivity.class);
        intent.putExtra(DetailedCountryActivity.COUNTRY_NAME_TAG, countryName);
        context.startActivity(intent);
    }
}
