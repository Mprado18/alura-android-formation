package com.example.aluratravel.ui.utils;

import androidx.annotation.NonNull;

public class DaysUtil {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    @NonNull
    public static String daysFormatter(int daysUnformatted) {
        if (daysUnformatted > 1) {
            return daysUnformatted + PLURAL;
        }
        return daysUnformatted + SINGULAR;
    }

}
