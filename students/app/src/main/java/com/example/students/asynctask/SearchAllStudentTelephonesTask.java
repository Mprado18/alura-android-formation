package com.example.students.asynctask;

import android.os.AsyncTask;

import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;
import com.example.students.model.Telephone;

import java.util.List;

public class SearchAllStudentTelephonesTask extends AsyncTask<Void, Void, List<Telephone>> {

    private final TelephoneDAO telephoneDAO;
    private final Student student;
    private final StudentTelephoneFoundListener listener;

    public SearchAllStudentTelephonesTask(
            TelephoneDAO telephoneDAO,
            Student student,
            StudentTelephoneFoundListener listener) {

        this.telephoneDAO = telephoneDAO;
        this.student = student;
        this.listener = listener;
    }

    @Override
    protected List<Telephone> doInBackground(Void... voids) {
        return telephoneDAO.searchAllStudentTelephones(student.getId());
    }

    @Override
    protected void onPostExecute(List<Telephone> telephones) {
        super.onPostExecute(telephones);
        listener.whenFound(telephones);
    }

    public interface StudentTelephoneFoundListener {
        void whenFound(List<Telephone> telephoneList);
    }

}
