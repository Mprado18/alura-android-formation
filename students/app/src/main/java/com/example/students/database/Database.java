package com.example.students.database;

import static com.example.students.database.migrationsdb.MigrationsDB.MIGRATIONS;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.students.database.converters.CalendarConversor;
import com.example.students.database.dao.RoomStudentDao;
import com.example.students.model.Student;

@androidx.room.Database(
        entities = {Student.class},
        version = 3,
        exportSchema = false
)
@TypeConverters({CalendarConversor.class})
public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "student.db";

    public abstract RoomStudentDao getRoomStudentDAO();

    public static Database getInstance(Context context) {
        return Room.databaseBuilder(context, Database.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATIONS)
                .build();
    }

}
