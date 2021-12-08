package com.example.students.database;

import static com.example.students.database.migrationsdb.MigrationsDB.MIGRATIONS;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.students.database.converters.CalendarConversor;
import com.example.students.database.converters.TypePhoneConversor;
import com.example.students.database.dao.StudentDAO;
import com.example.students.database.dao.TelephoneDAO;
import com.example.students.model.Student;
import com.example.students.model.Telephone;

@androidx.room.Database(
        entities = {Student.class, Telephone.class},
        version = 5,
        exportSchema = false
)
@TypeConverters({CalendarConversor.class, TypePhoneConversor.class})
public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "student.db";

    public abstract StudentDAO getRoomStudentDAO();
    public abstract TelephoneDAO getTelephoneDAO();

    public static Database getInstance(Context context) {
        return Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                .addMigrations(MIGRATIONS)
                .build();
    }
}
