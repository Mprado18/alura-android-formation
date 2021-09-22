package com.example.students.ui.activity;

import static com.example.students.ui.activity.Constants.STUDENT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.students.R;
import com.example.students.model.Student;
import com.example.students.ui.viewmodel.StudentsListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsListActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "Lista de alunos";
    private final StudentsListViewModel viewModel = new StudentsListViewModel(this);


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

        viewModel.updateStudentsList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.student_list_item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.remove_student_menu) {
            viewModel.removeStudentDialog(item);
        }

        return super.onContextItemSelected(item);
    }

    private void renderStudentsForm() {
        startActivity(new Intent(
                StudentsListActivity.this,
                StudentsFormActivity.class)
        );
    }

    private void configureList() {
        ListView studentsList = findViewById(R.id.studentsList);

        viewModel.configureAdapter(studentsList);
        configureClickListener(studentsList);
        registerForContextMenu(studentsList);
    }

    private void configureClickListener(ListView studentsList) {
        studentsList.setOnItemClickListener((adapterView, view, position, id) -> {
            Student studentSelected = (Student) adapterView.getItemAtPosition(position);
            renderEditStudentForm(studentSelected);
        });
    }

    private void renderEditStudentForm(Student studentSelected) {
        Intent saveBundle = new Intent(StudentsListActivity.this,
                StudentsFormActivity.class
        );

        saveBundle.putExtra(STUDENT_KEY, studentSelected);
        startActivity(saveBundle);
    }

    private void configureAddNewStudentButton() {
        FloatingActionButton addNewStudent = findViewById(R.id.addNewStudent);
        addNewStudent.setOnClickListener(view -> renderStudentsForm());
    }
}
