package com.example.students.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Telephone;

public class SearchFirstStudentTelephoneTask extends AsyncTask<Void, Void, Telephone> {

    private final TelephoneDAO dao;
    private final int studentId;
    private final FirstTelephoneFoundListener listener;

    public SearchFirstStudentTelephoneTask(
            TelephoneDAO dao,
            int studentId,
            FirstTelephoneFoundListener listener) {
        this.dao = dao;
        this.studentId = studentId;
        this.listener = listener;
    }

    @Override
    protected Telephone doInBackground(Void... voids) {
        return dao.searchFirstStudentTelephone(studentId);
    }

    @Override
    protected void onPostExecute(Telephone telephone) {
        super.onPostExecute(telephone);
        listener.whenTelephoneFound(telephone);
    }

    public interface FirstTelephoneFoundListener {
        void whenTelephoneFound(Telephone telephoneFound);
    }
}
