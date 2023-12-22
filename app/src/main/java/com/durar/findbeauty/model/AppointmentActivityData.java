package com.durar.findbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppointmentActivityData {
    @SerializedName("$id")
    private String id;

    private String status;

    private List<AppointmentData> data;

    private String message;

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<AppointmentData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class AppointmentData {

        @SerializedName("$id")
        private String id;

        @SerializedName("Total_Count")
        private int totalCount;

        @SerializedName("Date")
        private String date;

        @SerializedName("Book_Date")
        private String bookDate;

        @SerializedName("SalonBook_Id")
        private int salonBookId;

        @SerializedName("Time")
        private String time;

        @SerializedName("Name")
        private String name;

        @SerializedName("Service")
        private String service;

        @SerializedName("Stylist")
        private String stylist;

        @SerializedName("Hours")
        private String hours;

        @SerializedName("Booking_Price")
        private double bookingPrice;

        @SerializedName("Booking_Status")
        private String bookingStatus;

        @SerializedName("NoPreference")
        private boolean noPreference;

        public String getId() {
            return id;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public String getDate() {
            return date;
        }

        public String getBookDate() {
            return bookDate;
        }

        public int getSalonBookId() {
            return salonBookId;
        }

        public String getTime() {
            return time;
        }

        public String getName() {
            return name;
        }

        public String getService() {
            return service;
        }

        public String getStylist() {
            return stylist;
        }

        public String getHours() {
            return hours;
        }

        public double getBookingPrice() {
            return bookingPrice;
        }

        public String getBookingStatus() {
            return bookingStatus;
        }

        public boolean isNoPreference() {
            return noPreference;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setBookDate(String bookDate) {
            this.bookDate = bookDate;
        }

        public void setSalonBookId(int salonBookId) {
            this.salonBookId = salonBookId;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setStylist(String stylist) {
            this.stylist = stylist;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }

        public void setBookingPrice(double bookingPrice) {
            this.bookingPrice = bookingPrice;
        }

        public void setBookingStatus(String bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

        public void setNoPreference(boolean noPreference) {
            this.noPreference = noPreference;
        }
    }
}
