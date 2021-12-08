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
import com.example.students.asynctask.SaveStudentDataTask;
import com.example.students.asynctask.SearchAllStudentTelephonesTask;
import com.example.students.asynctask.UpdateStudentDataTask;
import com.example.students.database.Database;
import com.example.students.database.dao.StudentDAO;
import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;
import com.example.students.model.Telephone;
import com.example.students.model.TypePhone;

import java.util.List;

public class StudentsFormActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE_NEW_STUDENT = "Formulário de novo Aluno";
    public static final String APPBAR_TITLE_EDIT_STUDENT = "Editar informações do Aluno";
    private EditText name;
    private EditText lastName;
    private EditText phone;
    private EditText cellphone;
    private EditText email;
    private StudentDAO studentDAO;
    private Student student;
    private TelephoneDAO telephoneDAO;
    private List<Telephone> studentsTelephones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);

        Database database = Database.getInstance(this);
        studentDAO = database.getRoomStudentDAO();
        telephoneDAO = database.getTelephoneDAO();

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
        email.setText(student.getEmail());

        completeInputTelephoneNumbers(student);
    }

    private void completeInputTelephoneNumbers(Student student) {
        new SearchAllStudentTelephonesTask(telephoneDAO, student, telephones -> {
            this.studentsTelephones = telephones;
            for (Telephone telephone : studentsTelephones) {
                if (telephone.getType() == TypePhone.LANDLINE) {
                    phone.setText(telephone.getNumber());
                } else {
                    cellphone.setText(telephone.getNumber());
                }
            }
        }).execute();
    }

    private void finishAndSaveFormEdited() {
        createStudent();

        Telephone landline = getTelephoneNumber(phone, TypePhone.LANDLINE);
        Telephone cellphoneNumber = getTelephoneNumber(cellphone, TypePhone.CELLPHONE);

        if (student.hasValidId()) {
            updateStudentData(landline, cellphoneNumber);
        } else {
            saveStudentData(landline, cellphoneNumber);
        }

        finish();
    }

    private void initInputs() {
        name = findViewById(R.id.inputName);
        lastName = findViewById(R.id.inputLastName);
        phone = findViewById(R.id.inputPhone);
        cellphone = findViewById(R.id.inputCellphone);
        email = findViewById(R.id.inputEmail);
    }

    private Telephone getTelephoneNumber(EditText telephone, TypePhone typephone) {
        String phoneNumber = telephone.getText().toString();
        return new Telephone(phoneNumber, typephone);
    }

    private void saveStudentData(Telephone landline, Telephone cellphone) {
        new SaveStudentDataTask(studentDAO, student, landline, cellphone, telephoneDAO, this::finish)
                .execute();
    }

    private void updateStudentData(Telephone landline, Telephone cellphone) {
        new UpdateStudentDataTask(studentDAO, student, landline, cellphone, telephoneDAO,
                studentsTelephones,
                this::finish).execute();
    }

    private void createStudent() {
        String studentName = name.getText().toString();
        String studentLastName = lastName.getText().toString();
        String studentEmail = email.getText().toString();

        student.setName(studentName);
        student.setLastName(studentLastName);
        student.setEmail(studentEmail);
    }

}