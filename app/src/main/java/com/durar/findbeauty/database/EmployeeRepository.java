package com.durar.findbeauty.database;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.durar.findbeauty.model.EmployeeData;

import java.util.List;

public class EmployeeRepository {

    private final EmployeeDao employeeDao;
    private final LiveData<List<EmployeeData>> allEmployees;

    public EmployeeRepository(Application application) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        employeeDao = database.employeeDao();
        allEmployees = employeeDao.getAllEmployees();
    }

    public LiveData<List<EmployeeData>> getAllEmployees() {
        return allEmployees;
    }

    public void insert(EmployeeData employeeData) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> employeeDao.insert(employeeData));
    }

    public void deleteAll() {
        EmployeeDatabase.databaseWriteExecutor.execute(employeeDao::deleteAll);
    }
}

