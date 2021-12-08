package com.example.students.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Telephone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private TypePhone type;
    @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int studentId;

    public Telephone(String number, TypePhone type) {
        this.number = number;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public TypePhone getType() {
        return type;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
