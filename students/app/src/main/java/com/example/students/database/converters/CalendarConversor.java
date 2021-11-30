package com.example.students.database.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConversor {

    @TypeConverter
    public Long convertToLong(Calendar value) {
        if (value != null) {
            return value.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar convertToCalendar(Long value) {
        Calendar registrationTime = Calendar.getInstance();
        if (value != null) {
            registrationTime.setTimeInMillis(value);
        }
        return registrationTime;
    }

}
