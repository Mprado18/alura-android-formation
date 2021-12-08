package com.example.students.database.converters;

import androidx.room.TypeConverter;

import com.example.students.model.TypePhone;

public class TypePhoneConversor {

    @TypeConverter
    public String toString(TypePhone value) {
        return value.name();
    }

    @TypeConverter
    public TypePhone toTypePhone(String value) {
        if(value != null) {
            return TypePhone.valueOf(value);
        }
        return TypePhone.LANDLINE;
    }

}
