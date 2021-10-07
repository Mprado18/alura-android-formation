package com.example.aluratravel.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aluratravel.R;
import com.example.aluratravel.model.Packages;
import com.example.aluratravel.ui.constants.Constants;
import com.example.aluratravel.ui.utils.CurrencyUtil;
import com.example.aluratravel.ui.utils.DateUtil;
import com.example.aluratravel.ui.utils.DaysUtil;
import com.example.aluratravel.ui.utils.ResourceUtil;

public class PackageSummaryActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Resumo do pacote de viagem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_summary);
        setTitle(TITLE_APPBAR);

        getBundle();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.PACKAGE)) {
            final Packages pack = (Packages) intent.getSerializableExtra(Constants.PACKAGE);

            initializeValues(pack);
            configurePaymentButton(pack);
        }
    }

    private void initializeValues(Packages pack) {
        renderDestination(pack);
        renderImagePackage(pack);
        renderDays(pack);
        renderPrice(pack);
        renderDate(pack);
    }

    private void configurePaymentButton(Packages pack) {
        Button makePayment = findViewById(R.id.makePayment);
        makePayment.setOnClickListener(view -> renderPaymentActivity(pack));
    }

    private void renderPaymentActivity(Packages pack) {
        Intent intent = new Intent(PackageSummaryActivity.this, PaymentActivity.class);
        intent.putExtra(Constants.PACKAGE, pack);
        startActivity(intent);
    }

    private void renderDate(Packages pack) {
        TextView date = findViewById(R.id.packageDate);
        String dateOfTravelFormatted = DateUtil.dateFormatter(pack.getDays());
        date.setText(dateOfTravelFormatted);
    }

    private void renderPrice(Packages pack) {
        TextView price = findViewById(R.id.packagePrice);
        String moedaBrasileira = CurrencyUtil.currencyFormatter(pack.getValue());
        price.setText(moedaBrasileira);
    }

    private void renderDays(Packages pack) {
        TextView days = findViewById(R.id.packageDays);
        String diasEmTexto = DaysUtil.daysFormatter(pack.getDays());
        days.setText(diasEmTexto);
    }

    private void renderImagePackage(Packages pack) {
        ImageView image = findViewById(R.id.packageImageView);
        Drawable drawableDoPacote = ResourceUtil.getDrawable(this, pack.getImage());
        image.setImageDrawable(drawableDoPacote);
    }

    private void renderDestination(Packages pack) {
        TextView destination = findViewById(R.id.packageDestination);
        destination.setText(pack.getLocale());
    }
}