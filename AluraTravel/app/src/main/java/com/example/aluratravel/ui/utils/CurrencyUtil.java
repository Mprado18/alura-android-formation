package com.example.aluratravel.ui.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static final String PORTUGUESE_LANG = "pt";
    public static final String BRAZIL = "br";

    public static String currencyFormatter(BigDecimal value) {
        NumberFormat brasilianFormat = DecimalFormat.getCurrencyInstance(new Locale(
                PORTUGUESE_LANG, BRAZIL));
        return brasilianFormat.format(value);
    }

}
