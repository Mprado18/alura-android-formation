package com.example.students.asynctask;

import android.os.AsyncTask;

import com.example.students.model.Telephone;

abstract class BaseStudentWithTelephoneTask extends AsyncTask<Void, Void, Void> {

    private final FinishedListener listener;

    BaseStudentWithTelephoneTask(FinishedListener listener) {
        this.listener = listener;
    }

    void bindStudentWithTelephone(int studentId, Telephone... telephones) {
        for (Telephone telephone: telephones) {
            telephone.setStudentId(studentId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.whenFinished();
    }

    public interface FinishedListener {
        void whenFinished();
    }

}
