package com.durar.findbeauty.model;

import java.util.List;

public class SalesData {
    private String type;
    private String name;
    private int appointment;
    private double salesvalue;
    private double appointmentvalue;
    private boolean showInLegend;
    private List<DataPoint> dataPoints;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public double getSalesvalue() {
        return salesvalue;
    }

    public void setSalesvalue(double salesvalue) {
        this.salesvalue = salesvalue;
    }

    public double getAppointmentvalue() {
        return appointmentvalue;
    }

    public void setAppointmentvalue(double appointmentvalue) {
        this.appointmentvalue = appointmentvalue;
    }

    public boolean isShowInLegend() {
        return showInLegend;
    }

    public void setShowInLegend(boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}

class DataPoint {
    private double y;
    private String label;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

