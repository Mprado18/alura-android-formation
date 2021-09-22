package com.example.students.ui.viewmodel;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.students.dao.StudentsDAO;
import com.example.students.model.Student;
import com.example.students.ui.adapter.StudentsListAdapter;

public class StudentsListViewModel {

    private final Context context;
    private StudentsListAdapter adapter;
    private StudentsDAO dao;

    public StudentsListViewModel(Context context) {
        this.context = context;
        this.adapter = new StudentsListAdapter(this.context);
        this.dao = new StudentsDAO();
    }

    public void removeStudentDialog(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Remover aluno")
                .setMessage("Tem certeza que deseja remover o aluno da lista?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student studentSelected = adapter.getItem(menuInfo.position);
                    removeStudent(studentSelected);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void updateStudentsList() {
        adapter.clear();
        adapter.addAll(dao.allStudents());
    }

    public void removeStudent(Student studentSelected) {
        dao.removeStudent(studentSelected);
        adapter.remove(studentSelected);
    }

    public void configureAdapter(ListView studentsList) {
        adapter = new StudentsListAdapter(context);
        studentsList.setAdapter(adapter);
    }

}
