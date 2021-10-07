package com.example.aluratravel.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Packages implements Serializable {

    private final String locale;
    private final String image;
    private final int days;
    private final BigDecimal value;

    public Packages(String locale, String image, int days, BigDecimal value) {
        this.locale = locale;
        this.image = image;
        this.days = days;
        this.value = value;
    }

    public String getLocale() {
        return locale;
    }

    public String getImage() {
        return image;
    }

    public int getDays() {
        return days;
    }

    public BigDecimal getValue() {
        return value;
    }

}
