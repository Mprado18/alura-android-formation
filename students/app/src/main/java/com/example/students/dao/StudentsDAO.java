package com.example.students.dao;

import androidx.annotation.Nullable;

import com.example.students.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {

    private final static List<Student> students = new ArrayList<>();
    private static int countId = 1;

    public void saveStudent(Student student) {
        student.setId(countId);
        students.add(student);
        countId++;
    }

    public void updateStudent(Student student) {
        Student studentSelected = getStudentById(student);
        if (studentSelected != null) {
            int studentPosition = students.indexOf(studentSelected);
            students.set(studentPosition, student);
        }
    }

    public void removeStudent(Student student) {
        Student studentSelected = getStudentById(student);
        if (studentSelected != null) {
            students.remove(studentSelected);
        }
    }

    @Nullable
    private Student getStudentById(Student student) {
        for (Student std : students) {
            if (std.getId() == student.getId()) {
                return std;
            }
        }
        return null;
    }

    public List<Student> allStudents() {
        return new ArrayList<>(students);
    }

}
