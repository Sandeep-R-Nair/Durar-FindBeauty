package com.durar.findbeauty.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durar.findbeauty.databinding.AdapterAppointmentActivityItemBinding;
import com.durar.findbeauty.databinding.AdapterBirthdayItemBinding;
import com.durar.findbeauty.model.AppointmentActivityData;
import com.durar.findbeauty.model.BirthdayResponseData;

import java.text.DateFormatSymbols;
import java.util.List;

public class BirthdayActivityAdapter extends RecyclerView.Adapter<BirthdayActivityAdapter.ViewHolder> {

    private List<BirthdayResponseData> birthdayList;

    public BirthdayActivityAdapter(List<BirthdayResponseData> appointmentList) {
        this.birthdayList = appointmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterBirthdayItemBinding binding = AdapterBirthdayItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BirthdayResponseData bday = birthdayList.get(position);


        // Extracting day and month parts
        String[] dateParts = bday.getUserDOB().split("-");
        String day = dateParts[2];

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        String month = months[Integer.parseInt(dateParts[1])-1];  // Assuming month is numeric

        holder.binding.tvDate.setText(day);
        holder.binding.tvMonth.setText(month);
        holder.binding.tvName.setText(bday.getUserName());
        holder.binding.tvPhone.setText(bday.getMobile());
        holder.binding.tvEmail.setText(bday.getUserEmail());


    }

    @Override
    public int getItemCount() {
        return birthdayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterBirthdayItemBinding binding;

        public ViewHolder(AdapterBirthdayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setData(List<BirthdayResponseData> newData) {
        birthdayList.clear();
        birthdayList.addAll(newData);
        notifyDataSetChanged();
    }
}
