package com.durar.findbeauty.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durar.findbeauty.databinding.AdapterAppointmentActivityItemBinding;
import com.durar.findbeauty.databinding.AdapterUpcomingAppointmentItemBinding;
import com.durar.findbeauty.model.AppointmentActivityData;
import com.durar.findbeauty.model.UpcomingAppointmentData;

import java.text.DateFormatSymbols;
import java.util.List;

public class AppointmentActivityAdapter extends RecyclerView.Adapter<AppointmentActivityAdapter.ViewHolder> {

    private List<AppointmentActivityData.AppointmentData> appointmentList;

    public AppointmentActivityAdapter(List<AppointmentActivityData.AppointmentData> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterAppointmentActivityItemBinding binding = AdapterAppointmentActivityItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentActivityData.AppointmentData appointment = appointmentList.get(position);


        // Extracting day and month parts
        String[] dateParts = appointment.getDate().split("-");
        String day = dateParts[2];

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        String month = months[Integer.parseInt(dateParts[1])-1];  // Assuming month is numeric

        holder.binding.tvDate.setText(day);
        holder.binding.tvMonth.setText(month);
        holder.binding.tvService.setText(appointment.getService());
        holder.binding.tvBookedBy.setText("Booked by "+appointment.getName());
        holder.binding.tvTime.setText(appointment.getTime());


    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterAppointmentActivityItemBinding binding;

        public ViewHolder(AdapterAppointmentActivityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setData(List<AppointmentActivityData.AppointmentData> newData) {
        appointmentList.clear();
        appointmentList.addAll(newData);
        notifyDataSetChanged();
    }
}
