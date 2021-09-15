package com.example.students.dao;

import com.example.students.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

    private final static List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> allStudents() {
        return new ArrayList<>(students);
    }

}
