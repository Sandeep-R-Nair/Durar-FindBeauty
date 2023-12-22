package com.durar.findbeauty.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.durar.findbeauty.model.EmployeeData;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeData employeeData);

    @Query("DELETE FROM employee_table")
    void deleteAll();

    @Query("SELECT * FROM employee_table")
    LiveData<List<EmployeeData>> getAllEmployees();
}

