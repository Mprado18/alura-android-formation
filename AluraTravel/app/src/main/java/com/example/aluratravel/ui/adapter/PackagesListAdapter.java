package com.example.aluratravel.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluratravel.R;
import com.example.aluratravel.model.Packages;
import com.example.aluratravel.ui.utils.CurrencyUtil;
import com.example.aluratravel.ui.utils.DaysUtil;
import com.example.aluratravel.ui.utils.ResourceUtil;

import java.util.List;

public class PackagesListAdapter extends BaseAdapter {

    private final List<Packages> packages;
    private final Context context;

    public PackagesListAdapter(List<Packages> packages, Context context) {
        this.packages = packages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return packages.size();
    }

    @Override
    public Packages getItem(int position) {
        return packages.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View viewCreated = LayoutInflater.from(context)
                .inflate(R.layout.package_travel_item, parent, false);

        Packages pack = packages.get(position);

        renderLocalePackage(viewCreated, pack);
        renderImagePackage(viewCreated, pack);
        renderDaysPackage(viewCreated, pack);
        renderPricePackage(viewCreated, pack);

        return viewCreated;
    }

    private void renderPricePackage(View viewCreated, Packages pack) {
        TextView labelValue = viewCreated.findViewById(R.id.labelValue);

        String currencyBrasilianFormat = CurrencyUtil.currencyFormatter(pack.getValue());
        labelValue.setText(currencyBrasilianFormat);
    }

    private void renderDaysPackage(View viewCreated, Packages pack) {
        TextView labelDays = viewCreated.findViewById(R.id.labelDays);
        String daysFormatted = DaysUtil.daysFormatter(pack.getDays());

        labelDays.setText(daysFormatted);
    }

    private void renderImagePackage(View viewCreated, Packages pack) {
        ImageView imagePackageTravel = viewCreated.findViewById(R.id.imagePackgeTravel);
        Drawable drawable = ResourceUtil.getDrawable(context, pack.getImage());
        imagePackageTravel.setImageDrawable(drawable);
    }

    private void renderLocalePackage(View viewCreated, Packages pack) {
        TextView labelCity = viewCreated.findViewById(R.id.labelCity);
        labelCity.setText(pack.getLocale());
    }
}
