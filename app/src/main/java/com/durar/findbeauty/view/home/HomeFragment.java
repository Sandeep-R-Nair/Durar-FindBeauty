package com.durar.findbeauty.view.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.durar.findbeauty.R;
import com.durar.findbeauty.adapters.AppointmentActivityAdapter;
import com.durar.findbeauty.adapters.AppointmentAdapter;
import com.durar.findbeauty.adapters.BirthdayActivityAdapter;
import com.durar.findbeauty.adapters.EmployeeAdapter;
import com.durar.findbeauty.adapters.TopServicesAdapter;
import com.durar.findbeauty.databinding.FragmentHomeBinding;
import com.durar.findbeauty.model.AppointmentData;
import com.durar.findbeauty.model.SalesData;
import com.durar.findbeauty.adapters.CustomSpinnerAdapter;
import com.durar.findbeauty.viewmodel.HomeViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.taosif7.android.ringchartlib.RingChart;
import com.taosif7.android.ringchartlib.models.RingChartData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TopServicesAdapter topServicesAdapter;
    private int rowNumber = 0;
    private AppointmentAdapter appointmentAdapter;
    private AppointmentActivityAdapter appointmentActivityAdapter;
    private BirthdayActivityAdapter birthdayActivityAdapter;
    private EmployeeAdapter employeeAdapter;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize the binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setUpDropDown();

        binding.rvTopService.setLayoutManager(new LinearLayoutManager(requireActivity()));
        // Calculate the item height dynamically based on your layout
        int topServiceItemHeight = getResources().getDimensionPixelSize(R.dimen.top_service_recycler_item_height);
        // Set the maximum height to show only 5 items
        binding.rvTopService.getLayoutParams().height = topServiceItemHeight * 5;

        binding.rvUpcomingAppointments.setLayoutManager(new LinearLayoutManager(requireActivity()));
        appointmentAdapter = new AppointmentAdapter(new ArrayList<>());
        int upcomingAppointmentItemHeight = getResources().getDimensionPixelSize(R.dimen.upcomingAppointmentItemHeight);
        binding.rvAppointmentActivity.getLayoutParams().height = upcomingAppointmentItemHeight * 3;
        binding.rvUpcomingAppointments.setAdapter(appointmentAdapter);

        binding.rvAppointmentActivity.setLayoutManager(new LinearLayoutManager(requireActivity()));
        int appointmentActivityItemHeight = getResources().getDimensionPixelSize(R.dimen.appointmentActivityItemHeight);
        binding.rvAppointmentActivity.getLayoutParams().height = appointmentActivityItemHeight * 3;
        appointmentActivityAdapter = new AppointmentActivityAdapter(new ArrayList<>());
        binding.rvAppointmentActivity.setAdapter(appointmentActivityAdapter);

        binding.rvUpcomingBday.setLayoutManager(new LinearLayoutManager(requireActivity()));
        int birthdayItemHeight = getResources().getDimensionPixelSize(R.dimen.appointmentActivityItemHeight);
        binding.rvUpcomingBday.getLayoutParams().height = birthdayItemHeight * 3;
        birthdayActivityAdapter = new BirthdayActivityAdapter(new ArrayList<>());
        binding.rvUpcomingBday.setAdapter(birthdayActivityAdapter);

        employeeAdapter = new EmployeeAdapter(requireActivity());
        binding.rvTopStaff.setAdapter(employeeAdapter);
        binding.rvTopStaff.setLayoutManager(new GridLayoutManager(requireActivity(), 3));


        binding.rvUpcomingAppointments.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 3) {
                    // Load more data (pagination)
                    rowNumber += 3; // Increment row number by 3
                    homeViewModel.getUpcomingAppointmentData(rowNumber);
                }
            }
        });

        binding.rvAppointmentActivity.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 3) {
                    // Load more data (pagination)
                    rowNumber += 3; // Increment row number by 3
                    homeViewModel.getUpcomingAppointmentData(rowNumber);
                }
            }
        });

        binding.rvUpcomingBday.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 3) {
                    // Load more data (pagination)
                    rowNumber += 3; // Increment row number by 3
                    homeViewModel.getUpcomingAppointmentData(rowNumber);
                }
            }
        });

        binding.tvToday.setOnClickListener(view15 -> {
            getAppointmentStatus(1);
            binding.tvToday.setTextColor(getResources().getColor(R.color.white));
            binding.tvWeekly.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvMonthly.setTextColor(getResources().getColor(R.color.inactive_text_grey));
        });

        binding.tvWeekly.setOnClickListener(view14 -> {
            getAppointmentStatus(7);
            binding.tvToday.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvWeekly.setTextColor(getResources().getColor(R.color.white));
            binding.tvMonthly.setTextColor(getResources().getColor(R.color.inactive_text_grey));
        });

        binding.tvMonthly.setOnClickListener(view13 -> {
            getAppointmentStatus(30);
            binding.tvToday.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvWeekly.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvMonthly.setTextColor(getResources().getColor(R.color.white));
        });

        binding.tvWeeklySales.setOnClickListener(view12 -> {
            getSalesReport(7);
            binding.tvWeeklySales.setTextColor(getResources().getColor(R.color.white));
            binding.tvMonthlySales.setTextColor(getResources().getColor(R.color.inactive_text_grey));
        });

        binding.tvMonthlySales.setOnClickListener(view1 -> {
            getSalesReport(30);
            binding.tvWeeklySales.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvMonthlySales.setTextColor(getResources().getColor(R.color.white));
        });

        binding.tvThisMonth.setOnClickListener(view1 -> {
            getTopService(1);
            binding.tvThisMonth.setTextColor(getResources().getColor(R.color.black));
            binding.tvLastMonth.setTextColor(getResources().getColor(R.color.inactive_text_grey));
        });

        binding.tvLastMonth.setOnClickListener(view1 -> {
            getTopService(2);
            binding.tvThisMonth.setTextColor(getResources().getColor(R.color.inactive_text_grey));
            binding.tvLastMonth.setTextColor(getResources().getColor(R.color.black));
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
        checkLocationPermissionAndFetch();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Observe changes in the data
        homeViewModel.getDashboardData().observe(getViewLifecycleOwner(), dashboardData -> {
            // Handle the updated data here
            int customerCount = dashboardData.getCustomer();
            int visitorCount = dashboardData.getVisitor();
            int appointmentsCount = dashboardData.getAppointments();
            double salesAmount = dashboardData.getSales();

            // Update UI or perform other actions
            binding.tvVisitors.setText(String.valueOf(visitorCount));
            binding.tvCustomers.setText(String.valueOf(customerCount));
            binding.tvAppointmens.setText(String.valueOf(appointmentsCount));
            binding.tvSaleAmount.setText(String.valueOf(salesAmount));
        });
        homeViewModel.loadDashboardData();

        // Observe the LiveData
        homeViewModel.getUpcomingAppointmentData(rowNumber).observe(requireActivity(), appointmentList -> {
            // Update UI with the fetched data
            appointmentAdapter.setData(appointmentList);
        });

        homeViewModel.getAppointmentActivity(rowNumber).observe(requireActivity(), appointmentList -> {
            // Update UI with the fetched data
            appointmentActivityAdapter.setData(appointmentList);
        });

        homeViewModel.getBirthdayResponse(rowNumber).observe(requireActivity(), birthdayList -> {
            // Update UI with the fetched data
            birthdayActivityAdapter.setData(birthdayList);
        });

        homeViewModel.getEmployeeData().observe(requireActivity(), employees -> {
            employeeAdapter.setEmployees(employees);
        });


        getAppointmentStatus(1);
        getSalesReport(7);
        getTopService(1);
    }


    private void getTopService(int month) {

        topServicesAdapter = new TopServicesAdapter(new ArrayList<>(), month);
        binding.rvTopService.setAdapter(topServicesAdapter);
        // Observe the LiveData
        homeViewModel.getTopServicesData().observe(getViewLifecycleOwner(), topServicesList -> {
            // Update UI with the fetched data
            topServicesAdapter.updateData(topServicesList);

        });
        // Trigger initial data load
        homeViewModel.loadTopServicesData();

        // Implement pagination on RecyclerView
        binding.rvTopService.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!homeViewModel.isLoading() && homeViewModel.hasMoreData() &&
                        (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    homeViewModel.loadMoreData();
                }
            }
        });
    }

    private void setUpDropDown() {

        List<String> spinnerValues = Arrays.asList("Amount", "Hours");

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(requireActivity(), R.layout.custom_spinner_item, spinnerValues);
        binding.spinnerStaffValues.setAdapter(adapter);

        binding.spinnerStaffValues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = spinnerValues.get(position);
                homeViewModel.loadEmployeeData(selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void getSalesReport(int duration) {
        homeViewModel.getSalesData(duration).observe(getViewLifecycleOwner(), salesDataList -> {
            // Update UI with the fetched data
            // For example, you can display the data in a chart or any other UI component
            requireActivity().runOnUiThread(() -> {
                // Update UI with the fetched data

                for (SalesData salesData : salesDataList) {
                    // Handle each SalesData object
                    binding.tvAppointCount.setText(String.valueOf(salesDataList.get(0).getAppointment()));
                    binding.tvTotalSales.setText(String.valueOf(salesDataList.get(0).getSalesvalue()));
                    binding.tvTAppointValue.setText(String.valueOf(salesDataList.get(0).getAppointmentvalue()));

                }
            });


        });
        homeViewModel.loadSalesData(duration);
    }

    private void getAppointmentStatus(int duration) {
        binding.ringChart.setLayoutMode(RingChart.renderMode.MODE_CONCENTRIC);

        homeViewModel.getAppointmentData(duration).observe(getViewLifecycleOwner(), appointmentData -> {
            Log.d("RingChartTest", "LiveData observed: " + appointmentData.size() + " items");

            binding.civColor1.setCardBackgroundColor(Color.parseColor(appointmentData.get(0).getColor()));
            binding.tvSales1.setText(String.valueOf(appointmentData.get(0).getCount()));
            binding.tvSalesStatus1.setText(String.valueOf(appointmentData.get(0).getLabel()));

            binding.civColor2.setCardBackgroundColor(Color.parseColor(appointmentData.get(1).getColor()));
            binding.tvSales2.setText(String.valueOf(appointmentData.get(1).getCount()));
            binding.tvSalesStatus2.setText(String.valueOf(appointmentData.get(1).getLabel()));

            binding.civColor3.setCardBackgroundColor(Color.parseColor(appointmentData.get(2).getColor()));
            binding.tvSales3.setText(String.valueOf(appointmentData.get(2).getCount()));
            binding.tvSalesStatus3.setText(String.valueOf(appointmentData.get(2).getLabel()));

            List<RingChartData> dataPoints = convertToRingChartData(appointmentData);
//            binding.ringChart.setData(dataPoints); //not working

        });
        homeViewModel.fetchAppointmentStatus(duration);

        setDummyChart();
    }

    private void setDummyChart() {
        // Prepare data points
        RingChartData dp_red = new RingChartData(
                0.15f, // Float value between 0.0 and 1.0 inclusive
                ContextCompat.getColor(requireActivity(), R.color.red), // Resolved colour int
                "" // Label to uniquely identify this datapoint
        );
        RingChartData dp_green = new RingChartData(0.35f, ContextCompat.getColor(requireActivity(), R.color.green), "");
        RingChartData dp_blue = new RingChartData(0.65f, ContextCompat.getColor(requireActivity(), R.color.blue), "");

        // Make a list of data points
        ArrayList<RingChartData> data_list = new ArrayList<RingChartData>() {{
            add(dp_green);
            add(dp_red);
            add(dp_blue);
        }};

        // Set data to the instance
        binding.ringChart.setData(data_list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding
    }

    private List<RingChartData> convertToRingChartData(List<AppointmentData> appointmentDataList) {
        List<RingChartData> ringChartDataList = new ArrayList<>();

        for (AppointmentData appointmentData : appointmentDataList) {
            RingChartData data = new RingChartData(
                    (float) appointmentData.getY(),
                    android.graphics.Color.parseColor(appointmentData.getColor()),
                    appointmentData.getLabel()
            );
            ringChartDataList.add(data);
        }

        return ringChartDataList;
    }

    private void checkLocationPermissionAndFetch() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, fetch location
            fetchLocation();
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE
            );
        }
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), location -> {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        new GetLocationTask(requireContext()).execute(latitude, longitude);

                    } else {
                        // Location is null, handle accordingly
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch location
                fetchLocation();
            } else {
                // Permission denied, handle accordingly
            }
        }
    }

    private void showToast(String message) {
        // Implement your own showToast method or use Toast.makeText()
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    public class GetLocationTask extends AsyncTask<Double, Void, GetLocationTask.Result> {

        private Context context;

        GetLocationTask(Context context) {
            this.context = context;
        }

        // Result class to hold both locationName and subLocality
        static class Result {
            String locationName;
            String subLocality;
        }

        @Override
        protected Result doInBackground(Double... params) {
            double latitude = params[0];
            double longitude = params[1];

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            Result result = new Result();

            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null && addresses.size() > 0) {
                    Address address = addresses.get(0);
                    Log.d("TAG", "Location:: \n" + address.toString());
                    // You can customize the format of the location name based on your requirements
                    result.locationName = address.getCountryName();
                    result.subLocality = address.getLocality();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.locationName != null && !result.locationName.isEmpty()) {
                // Use result.subLocality as needed
                binding.tvLocation1.setText(result.locationName);
                binding.tvLocation2.setText(result.subLocality);
            } else {
                Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
