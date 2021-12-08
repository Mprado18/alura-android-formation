package com.example.students.asynctask;

import android.os.AsyncTask;

import com.example.students.database.dao.StudentDAO;
import com.example.students.model.Student;
import com.example.students.ui.adapter.StudentsListAdapter;

public class RemoveStudentTask extends AsyncTask<Void, Void, Void> {

    private final StudentDAO dao;
    private final StudentsListAdapter adapter;
    private final Student studentSelected;

    public RemoveStudentTask(StudentDAO dao,
                             StudentsListAdapter adapter,
                             Student studentSelected) {
        this.dao = dao;
        this.adapter = adapter;
        this.studentSelected = studentSelected;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.removeStudent(studentSelected);
        return null;
    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        adapter.remove(studentSelected);
    }
}
