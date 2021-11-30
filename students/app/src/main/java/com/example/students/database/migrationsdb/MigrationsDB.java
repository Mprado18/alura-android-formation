package com.example.students.database.migrationsdb;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MigrationsDB {

    private static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN lastName TEXT");
        }
    };

    private static final Migration MIGRATION_2_TO_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN registrationTime INTEGER");
        }
    };

    public static final Migration[] MIGRATIONS = {MIGRATION_1_TO_2, MIGRATION_2_TO_3};

}
