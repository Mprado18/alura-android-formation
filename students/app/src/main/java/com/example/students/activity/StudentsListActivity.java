package com.example.students.activity;

import static com.example.students.activity.Constants.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.dao.StudentsDAO;
import com.example.students.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Lista de alunos";
    private final StudentsDAO dao = new StudentsDAO();
    private ArrayAdapter<Student> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        setTitle(APPBAR_TITLE);
        configureAddNewStudentButton();
        configureList();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateStudentsList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.student_list_item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.remove_student_menu) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student studentSelected = adapter.getItem(menuInfo.position);
            removeStudent(studentSelected);
        }

        return super.onContextItemSelected(item);
    }

    private void updateStudentsList() {
        adapter.clear();
        adapter.addAll(dao.allStudents());
    }

    private void renderStudentsForm() {
        startActivity(new Intent(
                        StudentsListActivity.this,
                        StudentsFormActivity.class)
        );
    }

    private void configureList() {
        ListView studentsList = findViewById(R.id.studentsList);

        configureAdapter(studentsList);
        configureClickListener(studentsList);
        registerForContextMenu(studentsList);
    }

    private void removeStudent(Student studentSelected) {
        dao.removeStudent(studentSelected);
        adapter.remove(studentSelected);
    }

    private void configureClickListener(ListView studentsList) {
        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student studentSelected = (Student) adapterView.getItemAtPosition(position);
                renderEditStudentForm(studentSelected);
            }
        });
    }

    private void renderEditStudentForm(Student studentSelected) {
        Intent saveBundle = new Intent(StudentsListActivity.this,
                StudentsFormActivity.class
        );

        saveBundle.putExtra(STUDENT_KEY, studentSelected);
        startActivity(saveBundle);
    }

    private void configureAdapter(ListView studentsList) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        studentsList.setAdapter(adapter);
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
