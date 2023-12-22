package com.durar.findbeauty.model;

import androidx.room.Entity;

@Entity(tableName = "employee_table")

public class EmployeeData {
    private String empName;
    private String empDesignation;
    private String empEmployeeProfileImg;
    private String empGender;
    private double empRatingSummary;
    private Double thisMonth;
    private Double lastMonth;
    private String thisMonthHours;
    private Integer mints;
    private Integer lastMonthHours;

    public String getEmpName() {
        return empName;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public String getEmpEmployeeProfileImg() {
        return empEmployeeProfileImg;
    }

    public String getEmpGender() {
        return empGender;
    }

    public double getEmpRatingSummary() {
        return empRatingSummary;
    }

    public Double getThisMonth() {
        return thisMonth;
    }

    public Double getLastMonth() {
        return lastMonth;
    }

    public String getThisMonthHours() {
        return thisMonthHours;
    }

    public Integer getMints() {
        return mints;
    }

    public Integer getLastMonthHours() {
        return lastMonthHours;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    public void setEmpEmployeeProfileImg(String empEmployeeProfileImg) {
        this.empEmployeeProfileImg = empEmployeeProfileImg;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public void setEmpRatingSummary(double empRatingSummary) {
        this.empRatingSummary = empRatingSummary;
    }

    public void setThisMonth(Double thisMonth) {
        this.thisMonth = thisMonth;
    }

    public void setLastMonth(Double lastMonth) {
        this.lastMonth = lastMonth;
    }

    public void setThisMonthHours(String thisMonthHours) {
        this.thisMonthHours = thisMonthHours;
    }

    public void setMints(Integer mints) {
        this.mints = mints;
    }

    public void setLastMonthHours(Integer lastMonthHours) {
        this.lastMonthHours = lastMonthHours;
    }
}
