package com.example.aluratravel.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluratravel.R;
import com.example.aluratravel.model.Packages;
import com.example.aluratravel.ui.constants.Constants;
import com.example.aluratravel.ui.utils.CurrencyUtil;
import com.example.aluratravel.ui.utils.DateUtil;
import com.example.aluratravel.ui.utils.ResourceUtil;

public class PurchaseSummaryActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Resumo da compra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_summary);
        setTitle(APPBAR_TITLE);

        getBundle();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.PACKAGE)) {
            Packages pack = (Packages) intent.getSerializableExtra(Constants.PACKAGE);

            initializeValues(pack);
        }
    }

    private void initializeValues(Packages pack) {
        renderDestination(pack);
        renderImagePackage(pack);
        renderDate(pack);
        renderPrice(pack);
    }

    private void renderPrice(Packages pack) {
        TextView price = findViewById(R.id.labelPrice);
        String currency = CurrencyUtil
                .currencyFormatter(pack.getValue());
        price.setText(currency);
    }

    private void renderDate(Packages pack) {
        TextView date = findViewById(R.id.labelDate);
        String dateUnformatted = DateUtil
                .dateFormatter(pack.getDays());
        date.setText(dateUnformatted);
    }

    private void renderImagePackage(Packages pack) {
        ImageView imagePackage = findViewById(R.id.imagePackage);
        Drawable drawableDoPacote = ResourceUtil
                .getDrawable(this, pack.getImage());
        imagePackage.setImageDrawable(drawableDoPacote);
    }

    private void renderDestination(Packages pack) {
        TextView destination = findViewById(R.id.labelDestination);
        destination.setText(pack.getLocale());
    }
}