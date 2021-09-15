package com.example.students.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.dao.StudentsDAO;
import com.example.students.model.Student;

public class StudentsFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Formulário de novo Aluno";
    private EditText name;
    private EditText phone;
    private EditText email;
    private final StudentsDAO dao = new StudentsDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);

        setTitle(APPBAR_TITLE);
        initInputs();
        configureButtonSaveData();
    }

    private void initInputs() {
        name = findViewById(R.id.inputName);
        phone = findViewById(R.id.inputPhone);
        email = findViewById(R.id.inputEmail);
    }

    @NonNull
    private Student createStudent() {
        String studentName = name.getText().toString();
        String studentPhone = phone.getText().toString();
        String studentEmail = email.getText().toString();

        return new Student(studentName, studentPhone, studentEmail);
    }

    private void configureButtonSaveData() {
        Button buttonSaveData = findViewById(R.id.buttonSaveData);
        buttonSaveData.setOnClickListener(view -> {

            Student student = createStudent();
            saveDataStudent(student);
        });
    }

    private void saveDataStudent(Student student) {
        dao.save(student);
        finish();
    }
}