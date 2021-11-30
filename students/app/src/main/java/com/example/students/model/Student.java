package com.example.students.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Calendar registrationTime = Calendar.getInstance();

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationTime(Calendar registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getRegistrationTime() {
        return registrationTime;
    }

    @NonNull
    @Override
    public String toString() {
        return name + "-" + phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean hasValidId() {
        return id > 0;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public String getFormattedDate() {
        SimpleDateFormat formatator = new SimpleDateFormat("dd/MM/yyyy");
        return formatator.format(registrationTime.getTime());
    }
}
