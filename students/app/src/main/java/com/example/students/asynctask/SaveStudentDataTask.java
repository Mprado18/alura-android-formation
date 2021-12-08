package com.example.students.asynctask;

import com.example.students.database.dao.StudentDAO;
import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;
import com.example.students.model.Telephone;

public class SaveStudentDataTask extends BaseStudentWithTelephoneTask {

    private final StudentDAO studentDAO;
    private final Student student;
    private final Telephone phone;
    private final Telephone cellphone;
    private final TelephoneDAO telephoneDAO;

    public SaveStudentDataTask(
            StudentDAO studentDAO,
            Student student,
            Telephone phone,
            Telephone cellphone,
            TelephoneDAO telephoneDAO,
            FinishedListener listener) {
        super(listener);
        this.studentDAO = studentDAO;
        this.student = student;
        this.phone = phone;
        this.cellphone = cellphone;
        this.telephoneDAO = telephoneDAO;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        int studentId = studentDAO.saveStudent(student).intValue();
        bindStudentWithTelephone(studentId, phone, cellphone);
        telephoneDAO.saveTelephone(phone, cellphone);
        return null;
    }

}
