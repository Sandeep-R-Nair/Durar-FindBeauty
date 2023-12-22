package com.durar.findbeauty.model;

public class DashboardData {

    private int customer;
    private int visitor;
    private int appointments;
    private double sales;

    public DashboardData(int customer, int visitor, int appointments, double sales) {
        this.customer = customer;
        this.visitor = visitor;
        this.appointments = appointments;
        this.sales = sales;
    }

    public int getCustomer() {
        return customer;
    }

    public int getVisitor() {
        return visitor;
    }

    public int getAppointments() {
        return appointments;
    }

    public double getSales() {
        return sales;
    }
}

