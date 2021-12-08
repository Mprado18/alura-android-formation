package com.example.students.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.students.R;
import com.example.students.asynctask.SearchFirstStudentTelephoneTask;
import com.example.students.database.Database;
import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsListAdapter extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;
    private final TelephoneDAO dao;

    public StudentsListAdapter(Context context) {
        this.context = context;
        dao = Database.getInstance(context).getTelephoneDAO();
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View viewCreated = LayoutInflater
                .from(context)
                .inflate(R.layout.student_item, viewGroup, false);

        Student studentSelected = students.get(position);

        TextView studentName = viewCreated.findViewById(R.id.studentName);
        studentName.setText(studentSelected.getFullName());

        TextView studentPhone = viewCreated.findViewById(R.id.studentPhone);
        new SearchFirstStudentTelephoneTask(dao, studentSelected.getId(), telephoneFound ->
                studentPhone.setText(String.format("Telefone: %s", telephoneFound.getNumber()))).execute();

        TextView registrationTime = viewCreated.findViewById(R.id.registrationTime);
        registrationTime.setText(String.format(
                "Data de cadastro: %s", studentSelected.getFormattedDate())
        );

        return viewCreated;
    }

    public void addAll(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student studentSelected) {
        students.remove(studentSelected);
        notifyDataSetChanged();
    }
}
