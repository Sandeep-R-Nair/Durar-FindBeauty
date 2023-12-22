package com.durar.findbeauty.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.durar.findbeauty.model.AppointmentActivityData;
import com.durar.findbeauty.model.AppointmentData;
import com.durar.findbeauty.model.BirthdayResponseData;
import com.durar.findbeauty.model.DashboardData;
import com.durar.findbeauty.model.EmployeeData;
import com.durar.findbeauty.model.SalesData;
import com.durar.findbeauty.model.TopServiceData;
import com.durar.findbeauty.model.UpcomingAppointmentData;
import com.durar.findbeauty.utils.ApiConstants;
import com.durar.findbeauty.utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<DashboardData> dashboardData;
    private MutableLiveData<List<AppointmentData>> appointmentData;
    private MutableLiveData<List<SalesData>> salesData;
    private MutableLiveData<List<TopServiceData>> topServicesData;
    private MutableLiveData<List<UpcomingAppointmentData.AppointmentData>> upcomingAppointmentData;
    private MutableLiveData<List<EmployeeData>> employeeData;
    private MutableLiveData<List<AppointmentActivityData.AppointmentData>> appointmentActivityData;
    private MutableLiveData<List<BirthdayResponseData>> birthdayData;
    private int currentPage = 0;
    private boolean isLoading = false;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        employeeData = new MutableLiveData<>();
        loadEmployeeData("Amount"); // Default Status is "Amount"
    }

    public LiveData<DashboardData> getDashboardData() {
        if (dashboardData == null) {
            dashboardData = new MutableLiveData<>();
            loadDashboardData();
        }
        return dashboardData;
    }

    public LiveData<List<AppointmentData>> getAppointmentData(int duration) {

        if (appointmentData == null) {
            appointmentData = new MutableLiveData<>();
            fetchAppointmentStatus(duration);
        }
        return appointmentData;
    }

    public LiveData<List<SalesData>> getSalesData(int duration) {
        if (salesData == null) {
            salesData = new MutableLiveData<>();
            loadSalesData(duration);
        }
        return salesData;
    }

    public LiveData<List<TopServiceData>> getTopServicesData() {
        if (topServicesData == null) {
            topServicesData = new MutableLiveData<>();
            loadTopServicesData();
        }
        return topServicesData;
    }

    public LiveData<List<UpcomingAppointmentData.AppointmentData>> getUpcomingAppointmentData(int rowNumber) {
        if (upcomingAppointmentData == null) {
            upcomingAppointmentData = new MutableLiveData<>();
            loadUpcomingAppointmentData(rowNumber);
        }
        return upcomingAppointmentData;
    }

    public LiveData<List<AppointmentActivityData.AppointmentData>> getAppointmentActivity(int rowNumber) {
        if (appointmentActivityData == null) {
            appointmentActivityData = new MutableLiveData<>();
            loadAppointmentActivityData(rowNumber);
        }
        return appointmentActivityData;
    }
    public LiveData<List<BirthdayResponseData>> getBirthdayResponse(int rowNumber) {
        if (birthdayData == null) {
            birthdayData = new MutableLiveData<>();
            getBirthdayData(rowNumber);
        }
        return birthdayData;
    }

    public LiveData<List<EmployeeData>> getEmployeeData() {
        return employeeData;
    }


    public void loadDashboardData() {
        String url = ApiConstants.BASE_URL + ApiConstants.DASHBOARD_DETAILS_URL;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            if (data.length() > 0) {
                                JSONObject dashboardObject = data.getJSONObject(0);
                                DashboardData dashboard = new DashboardData(
                                        dashboardObject.getInt("Customer"),
                                        dashboardObject.getInt("Visitor"),
                                        dashboardObject.getInt("Appointments"),
                                        dashboardObject.getDouble("Sales")
                                );
                                dashboardData.setValue(dashboard);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }

    public void fetchAppointmentStatus(int duration) {
        String url = ApiConstants.BASE_URL + ApiConstants.APPOINTMENT_STATUS_URL+ duration + "&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<AppointmentData> dataList = new ArrayList<>();

                        try {
                            String status = response.getString("status");

                            if ("success".equals(status)) {
                                JSONArray data = response.getJSONArray("data");

                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject appointmentObject = data.getJSONObject(i);

                                    AppointmentData appointment = new AppointmentData();
                                    appointment.setLabel(appointmentObject.getString("label"));
                                    appointment.setY(appointmentObject.getDouble("y"));
                                    appointment.setCount(appointmentObject.getInt("Count"));
                                    appointment.setColor(appointmentObject.getString("color"));
                                    dataList.add(appointment);
                                }

                                // Update LiveData with the parsed data
                                appointmentData.setValue(dataList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }

    public void loadSalesData(int duration) {
        String url = ApiConstants.BASE_URL+ApiConstants.SALES_DATA_URL + duration + "&AllSalon=0&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<SalesData> salesList = new ArrayList<>();

                        try {
                            String status = response.getString("status");

                            if ("success".equals(status)) {
                                JSONArray dataArray = response.getJSONArray("data");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject salesObject = dataArray.getJSONObject(i);
                                    SalesData salesData = new Gson().fromJson(salesObject.toString(), SalesData.class);
                                    salesList.add(salesData);
                                }

                                salesData.setValue(salesList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }

    public void loadTopServicesData() {
        String url = ApiConstants.BASE_URL+ApiConstants.TOP_SERVICES_URL + currentPage + "&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");

                            if ("success".equals(status)) {
                                List<TopServiceData> topServicesList = new ArrayList<>();
                                JSONArray dataArray = response.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject serviceObject = dataArray.getJSONObject(i);
                                    TopServiceData serviceData = new TopServiceData();
                                    serviceData.setService_Name(serviceObject.getString("Service_Name"));
                                    serviceData.setThisMonth(serviceObject.getInt("ThisMonth"));
                                    serviceData.setLastMonth(serviceObject.getInt("LastMonth"));
                                    topServicesList.add(serviceData);
                                }

                                topServicesData.setValue(topServicesList);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);

    }

    private void loadUpcomingAppointmentData(int rowNumber) {
        String url = ApiConstants.BASE_URL+ ApiConstants.UPCOMING_APPOINTMENT_URL + rowNumber + "&AllSalon=0&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<UpcomingAppointmentData.AppointmentData> appointmentList = new ArrayList<>();

                        JSONArray dataArray = response.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject appointmentObject = dataArray.getJSONObject(i);
                            UpcomingAppointmentData.AppointmentData appointment = new UpcomingAppointmentData.AppointmentData();
                            appointment.setSalonBookId(appointmentObject.getInt("SalonBook_Id"));
                            appointment.setDate(appointmentObject.getString("Date"));
                            appointment.setTime(appointmentObject.getString("Time"));
                            appointment.setName(appointmentObject.getString("Name"));
                            appointment.setService(appointmentObject.getString("Service"));
                            appointment.setStylist(appointmentObject.getString("Stylist"));
                            appointment.setHours(appointmentObject.getString("Hours"));
                            appointment.setBookingPrice(appointmentObject.getDouble("Booking_Price"));
                            appointment.setBookingStatus(appointmentObject.getString("Booking_Status"));
                            appointment.setNoPreference(appointmentObject.getBoolean("NoPreference"));

                            appointmentList.add(appointment);
                        }

                        upcomingAppointmentData.setValue(appointmentList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                    error.printStackTrace();
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }

    public void loadEmployeeData(String status) {
        String url = ApiConstants.BASE_URL+ApiConstants.TOP_STAFF_URL + status + "&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<EmployeeData> employeeList = new ArrayList<>();

                        JSONArray dataArray = response.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject employeeObject = dataArray.getJSONObject(i);
                            EmployeeData employeeData = new EmployeeData();
                            employeeData.setEmpName(employeeObject.getString("Emp_Name"));
                            employeeData.setEmpDesignation(employeeObject.getString("Emp_Designation"));
                            employeeData.setEmpEmployeeProfileImg(employeeObject.optString("Emp_EmployeeProfileImg"));
                            employeeData.setEmpGender(employeeObject.getString("Emp_Gender"));
                            employeeData.setEmpRatingSummary(employeeObject.getDouble("Emp_RatingSummary"));
                            employeeData.setThisMonth(employeeObject.isNull("ThisMonth") ? null : employeeObject.getDouble("ThisMonth"));
                            employeeData.setLastMonth(employeeObject.isNull("LastMonth") ? null : employeeObject.getDouble("LastMonth"));
                            employeeData.setThisMonthHours(employeeObject.isNull("ThisMonthHours") ? null : employeeObject.getString("ThisMonthHours"));
                            employeeData.setMints(employeeObject.isNull("Mints") ? null : employeeObject.getInt("Mints"));
                            employeeData.setLastMonthHours(employeeObject.isNull("LastMonthHours") ? null : employeeObject.getInt("LastMonthHours"));
                            employeeList.add(employeeData);
                        }

                        employeeData.setValue(employeeList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                    error.printStackTrace();
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }

    private void loadAppointmentActivityData(int rowNumber) {
        String url = ApiConstants.BASE_URL+ ApiConstants.APPOINTMENT_ACTIVITY_URL + rowNumber + "&AllSalon=0&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<AppointmentActivityData.AppointmentData> appointmentList = new ArrayList<>();

                        JSONArray dataArray = response.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject appointmentObject = dataArray.getJSONObject(i);
                            AppointmentActivityData.AppointmentData activityData = new AppointmentActivityData.AppointmentData();
                            activityData.setDate(appointmentObject.getString("Book_Date"));
                            activityData.setService(appointmentObject.getString("Service"));
                            activityData.setName(appointmentObject.getString("Name"));
                            activityData.setTime(appointmentObject.getString("Time"));

                            appointmentList.add(activityData);
                        }

                        appointmentActivityData.setValue(appointmentList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                    error.printStackTrace();
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }


    private void getBirthdayData(int rowNumber) {
        String url = ApiConstants.BASE_URL+ ApiConstants.UPCOMING_BDAY_URL + rowNumber + "&AllSalon=0&Lang=en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<BirthdayResponseData> birthdayList = new ArrayList<>();

                        JSONArray dataArray = response.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject birthdaysObject = dataArray.getJSONObject(i);
                            BirthdayResponseData birthdayData = new BirthdayResponseData();
                            birthdayData.setUserName(birthdaysObject.getString("User_Name"));
                            birthdayData.setMobile(birthdaysObject.getString("Mobile"));
                            birthdayData.setUserEmail(birthdaysObject.getString("User_Email"));

                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

                            try {
                                Date date = inputFormat.parse(birthdaysObject.getString("User_DOB"));
                                if (date != null) {
                                    birthdayData.setUserDOB(outputFormat.format(date));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            birthdayList.add(birthdayData);
                        }

                        birthdayData.setValue(birthdayList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Handle error
                    error.printStackTrace();
                }
        ){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", ApiConstants.TOKEN);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getApplication()).addToRequestQueue(jsonObjectRequest);
    }


    public void loadMoreData() {
        currentPage++;
        loadTopServicesData();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean hasMoreData() {
        // Add logic to determine if there's more data to load
        return true;
    }
}

