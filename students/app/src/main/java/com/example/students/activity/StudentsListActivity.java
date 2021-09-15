package com.example.students.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.dao.StudentsDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Lista de alunos";
    private final StudentsDAO dao = new StudentsDAO();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        setTitle(APPBAR_TITLE);
        configureAddNewStudentButton();
    }

    @Override
    protected void onResume() {
        super.onResume();

        configureList();
    }

    private void renderStudentsForm() {
        startActivity(new Intent(
                        StudentsListActivity.this,
                        StudentsFormActivity.class)
        );
    }

    private void configureList() {
        ListView studentsList = findViewById(R.id.studentsList);
        studentsList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.allStudents()
        ));
    }

    private void configureAddNewStudentButton() {
        FloatingActionButton addNewStudent = findViewById(R.id.addNewStudent);
        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renderStudentsForm();
            }
        });
    }
}
