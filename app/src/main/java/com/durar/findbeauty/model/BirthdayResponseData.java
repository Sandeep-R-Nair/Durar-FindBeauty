package com.durar.findbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BirthdayResponseData {

        @SerializedName("$id")
        private String id;

        @SerializedName("Total_Count")
        private int totalCount;

        @SerializedName("User_Id")
        private int userId;

        @SerializedName("User_Name")
        private String userName;

        @SerializedName("Mobile")
        private String mobile;

        @SerializedName("Telephone")
        private String telephone;

        @SerializedName("User_Email")
        private String userEmail;

        @SerializedName("User_Gender")
        private String userGender;

        @SerializedName("User_DOB")
        private String userDOB;

        @SerializedName("CurrentDOB")
        private String currentDOB;

        @SerializedName("NextDOB")
        private String nextDOB;

        @SerializedName("type")
        private String type;

        public String getId() {
            return id;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public int getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getMobile() {
            return mobile;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserGender() {
            return userGender;
        }

        public String getUserDOB() {
            return userDOB;
        }

        public String getCurrentDOB() {
            return currentDOB;
        }

        public String getNextDOB() {
            return nextDOB;
        }

        public String getType() {
            return type;
        }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public void setCurrentDOB(String currentDOB) {
        this.currentDOB = currentDOB;
    }

    public void setNextDOB(String nextDOB) {
        this.nextDOB = nextDOB;
    }

    public void setType(String type) {
        this.type = type;
    }
}
