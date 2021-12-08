package com.example.students.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.students.model.Telephone;

import java.util.List;

@Dao
public interface TelephoneDAO {

    @Query("SELECT * FROM Telephone WHERE studentId = :studentId LIMIT 1")
    Telephone searchFirstStudentTelephone(int studentId);

    @Insert
    void saveTelephone(Telephone... telephones);

    @Query("SELECT * FROM Telephone WHERE studentId = :studentId")
    List<Telephone> searchAllStudentTelephones(int studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void updateTelephones(Telephone... telephones);

}
