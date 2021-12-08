package com.example.students.asynctask;

import com.example.students.database.dao.StudentDAO;
import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;
import com.example.students.model.Telephone;
import com.example.students.model.TypePhone;

import java.util.List;

public class UpdateStudentDataTask extends BaseStudentWithTelephoneTask {
    private final StudentDAO studentDAO;
    private final Student student;
    private final Telephone landline;
    private final Telephone cellphone;
    private final TelephoneDAO telephoneDAO;
    private final List<Telephone> studentsTelephones;

    public UpdateStudentDataTask(StudentDAO studentDAO,
                                 Student student,
                                 Telephone landline,
                                 Telephone cellphone,
                                 TelephoneDAO telephoneDAO,
                                 List<Telephone> studentsTelephones,
                                 FinishedListener listener) {
        super(listener);
        this.studentDAO = studentDAO;
        this.student = student;
        this.landline = landline;
        this.cellphone = cellphone;
        this.telephoneDAO = telephoneDAO;
        this.studentsTelephones = studentsTelephones;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        studentDAO.updateStudent(student);
        bindStudentWithTelephone(student.getId(), landline, cellphone);
        updateIdOfTelephones(landline, cellphone);

        telephoneDAO.updateTelephones(landline, cellphone);
        return null;
    }

    private void updateIdOfTelephones(Telephone landline, Telephone cellphone) {
        for (Telephone telephone : studentsTelephones) {
            if (telephone.getType() == TypePhone.LANDLINE) {
                landline.setId(telephone.getId());
            } else {
                cellphone.setId(telephone.getId());
            }
        }
    }
}
