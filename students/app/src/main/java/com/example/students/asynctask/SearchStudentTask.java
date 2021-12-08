package com.example.students.asynctask;

import android.os.AsyncTask;

import com.example.students.database.dao.StudentDAO;
import com.example.students.model.Student;
import com.example.students.ui.adapter.StudentsListAdapter;

import java.util.List;

public class SearchStudentTask extends AsyncTask<Void, Void, List<Student>> {

    private final StudentDAO dao;
    private final StudentsListAdapter adapter;

    public SearchStudentTask(StudentDAO dao, StudentsListAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        return dao.allStudents();
    }

    @Override
    protected void onPostExecute(List<Student> allStudents) {
        super.onPostExecute(allStudents);
        adapter.addAll(allStudents);
    }
}
