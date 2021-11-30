package com.example.students.ui.activity;

import static com.example.students.ui.activity.Constants.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.database.Database;
import com.example.students.database.dao.RoomStudentDao;
import com.example.students.model.Student;

public class StudentsFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_NEW_STUDENT = "Formulário de novo Aluno";
    public static final String APPBAR_TITLE_EDIT_STUDENT = "Editar informações do Aluno";
    private EditText name;
    private EditText lastName;
    private EditText phone;
    private EditText email;
    private RoomStudentDao dao;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);

        Database database = Database.getInstance(this);
        dao = database.getRoomStudentDAO();

        initInputs();
        configureStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.save_form_menu) {
            finishAndSaveFormEdited();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureStudent() {
        Intent bundle = getIntent();
        if (bundle.hasExtra(STUDENT_KEY)) {
            setTitle(APPBAR_TITLE_EDIT_STUDENT);
            student = (Student) bundle.getSerializableExtra(STUDENT_KEY);
            completeStudentData(student);
        } else {
            setTitle(APPBAR_TITLE_NEW_STUDENT);
            student = new Student();
        }
    }

    private void completeStudentData(Student student) {
        name.setText(student.getName());
        lastName.setText(student.getLastName());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
    }

    private void initInputs() {
        name = findViewById(R.id.inputName);
        lastName = findViewById(R.id.inputLastName);
        phone = findViewById(R.id.inputPhone);
        email = findViewById(R.id.inputEmail);
    }

    private void finishAndSaveFormEdited() {
        createStudent();
        if (student.hasValidId()) {
            dao.updateStudent(student);
        } else {
            dao.saveStudent(student);
        }

        finish();
    }

    private void createStudent() {
        String studentName = name.getText().toString();
        String studentLastName = lastName.getText().toString();
        String studentPhone = phone.getText().toString();
        String studentEmail = email.getText().toString();

        student.setName(studentName);
        student.setLastName(studentLastName);
        student.setPhone(studentPhone);
        student.setEmail(studentEmail);
    }
}