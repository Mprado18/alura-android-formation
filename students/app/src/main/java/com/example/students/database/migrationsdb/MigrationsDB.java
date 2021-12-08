package com.example.students.database.migrationsdb;

import static com.example.students.model.TypePhone.LANDLINE;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.students.model.TypePhone;

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

    private static final Migration MIGRATION_3_TO_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //cria nova tabela no banco de dados
            database.execSQL("CREATE TABLE IF NOT EXISTS `New_Student` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`lastName` TEXT, " +
                    "`phone` TEXT, " +
                    "`cellphone` TEXT, " +
                    "`email` TEXT, " +
                    "`registrationTime` INTEGER)");

            //copia dados da tabela anterior para a nova
            database.execSQL("INSERT INTO New_Student (id, name, phone, email, registrationTime) " +
                    "SELECT id, name, phone, email, registrationTime FROM Student");

            //exclui tabela anterior
            database.execSQL("DROP TABLE Student");

            //renomeia tabela nova para nome da tabela antiga
            database.execSQL("ALTER TABLE New_Student RENAME TO Student");
        }
    };

    private static final Migration MIGRATION_4_TO_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Tabela Student
            database.execSQL("CREATE TABLE IF NOT EXISTS `New_Student` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`name` TEXT, " +
                    "`lastName` TEXT, " +
                    "`email` TEXT, " +
                    "`registrationTime` INTEGER)");

            database.execSQL("INSERT INTO New_Student (id, name, lastName, email, registrationTime) " +
                    "SELECT id, name, lastName, email, registrationTime FROM Student");

            database.execSQL("DROP TABLE Student");

            database.execSQL("ALTER TABLE New_Student RENAME TO Student");

            //Tabela Telephone
            database.execSQL("CREATE TABLE IF NOT EXISTS `Telephone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`number` TEXT, " +
                    "`type` TEXT, " +
                    "`studentId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Telephone (studentId) " +
                    "SELECT id FROM Student");

            database.execSQL("UPDATE Telephone SET type = ?", new TypePhone[] {LANDLINE});
        }
    };

    public static final Migration[] MIGRATIONS = {
            MIGRATION_1_TO_2,
            MIGRATION_2_TO_3,
            MIGRATION_3_TO_4,
            MIGRATION_4_TO_5
    };

}
