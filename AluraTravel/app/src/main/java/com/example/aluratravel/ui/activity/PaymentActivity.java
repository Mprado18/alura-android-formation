package com.example.aluratravel.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluratravel.R;
import com.example.aluratravel.model.Packages;
import com.example.aluratravel.ui.constants.Constants;
import com.example.aluratravel.ui.utils.CurrencyUtil;

public class PaymentActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Pagamento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle(APPBAR_TITLE);

        getBundle();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.PACKAGE)) {
            final Packages pack = (Packages) intent.getSerializableExtra(Constants.PACKAGE);

            showPrice(pack);
            configureConfirmPaymentButton(pack);
        }
    }

    private void configureConfirmPaymentButton(Packages pack) {
        Button confirmPayment = findViewById(R.id.confirmPayment);
        confirmPayment.setOnClickListener(view -> renderPurchaseSumaryActivity(pack));
    }

    private void renderPurchaseSumaryActivity(Packages pack) {
        Intent intent = new Intent(PaymentActivity.this, PurchaseSummaryActivity.class);
        intent.putExtra(Constants.PACKAGE, pack);
        startActivity(intent);
    }

    private void showPrice(Packages pack) {
        TextView price = findViewById(R.id.totalPrice);
        String brasilianCurrency = CurrencyUtil.currencyFormatter(pack.getValue());
        price.setText(brasilianCurrency);
    }
}