package com.example.students.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.students.model.Student;

import java.util.List;

@Dao
public interface RoomStudentDao {

    @Insert
    void saveStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> allStudents();

    @Delete
    void removeStudent(Student student);

    @Update
    void updateStudent(Student student);
}
