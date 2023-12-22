package com.durar.findbeauty.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.durar.findbeauty.model.EmployeeData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EmployeeData.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    private static volatile EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EmployeeDatabase.class, "employee_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
}
