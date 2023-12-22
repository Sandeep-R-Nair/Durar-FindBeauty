package com.durar.findbeauty.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durar.findbeauty.R;
import com.durar.findbeauty.databinding.AdapterUpcomingAppointmentItemBinding;
import com.durar.findbeauty.model.UpcomingAppointmentData;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<UpcomingAppointmentData.AppointmentData> appointmentList;

    public AppointmentAdapter(List<UpcomingAppointmentData.AppointmentData> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterUpcomingAppointmentItemBinding binding = AdapterUpcomingAppointmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpcomingAppointmentData.AppointmentData appointment = appointmentList.get(position);

        // Bind data to the ViewHolder
        holder.binding.tvServiceName.setText(appointment.getService());
        holder.binding.tvStaffName.setText(appointment.getName());
        holder.binding.tvServiceTime.setText(appointment.getDate()+"|"+appointment.getTime());

        // Add more bindings as needed
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterUpcomingAppointmentItemBinding binding;

        public ViewHolder(AdapterUpcomingAppointmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setData(List<UpcomingAppointmentData.AppointmentData> newData) {
        appointmentList.clear();
        appointmentList.addAll(newData);
        notifyDataSetChanged();
    }
}
