package com.rafagarcia.countries.main.detailedview.borderview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafagarcia.countries.R;

import java.util.List;

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

    Context context;
    BordersPresenter presenter;

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
        TextView textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.circle_shape);
        textView.setTextSize(14);
        textView.setTextColor(ContextCompat.getColor(context, R.color.color_primary));
        textView.setText(country);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20, 5, 20, 5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 20, 5, 10);
        textView.setLayoutParams(layoutParams);
        bordersLinearLayout.addView(textView);
    }
}
