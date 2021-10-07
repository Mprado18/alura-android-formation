package com.example.aluratravel.ui.utils;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static final String DAY_AND_MONTH = "dd/MM";

    @NonNull
    public static String dateFormatter(int days) {
        Calendar initialDate = Calendar.getInstance();
        Calendar finalDate = Calendar.getInstance();
        finalDate.add(Calendar.DATE, days);
        SimpleDateFormat brasilianDateFormat = new SimpleDateFormat(DAY_AND_MONTH);
        String initialDateFormatted = brasilianDateFormat.format(initialDate.getTime());
        String finalDateFormatted = brasilianDateFormat.format(finalDate.getTime());

        return initialDateFormatted + " - "
                + finalDateFormatted + " de "
                + finalDate.get(Calendar.YEAR);
    }
}
