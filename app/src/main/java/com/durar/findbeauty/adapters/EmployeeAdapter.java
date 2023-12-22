package com.durar.findbeauty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.durar.findbeauty.R;
import com.durar.findbeauty.databinding.AdapterTopStaffsItemBinding;
import com.durar.findbeauty.model.EmployeeData;
import com.durar.findbeauty.utils.ApiConstants;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private final LayoutInflater inflater;
    private List<EmployeeData> employees;

    public EmployeeAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterTopStaffsItemBinding binding = AdapterTopStaffsItemBinding.inflate(inflater, parent, false);
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        if (employees != null) {
            EmployeeData current = employees.get(position);
            holder.bind(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.bindEmpty();
        }
    }

    public void setEmployees(List<EmployeeData> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return employees != null ? employees.size() : 0;
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private final AdapterTopStaffsItemBinding binding;

        private EmployeeViewHolder(AdapterTopStaffsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(EmployeeData employee) {
            binding.tvName.setText(employee.getEmpName());
            binding.tvPosition.setText(employee.getEmpDesignation());
            binding.ratingBar.setRating((float)employee.getEmpRatingSummary());
            Glide.with(binding.tvName.getContext())
                    .load(ApiConstants.BASE_URL+employee.getEmpEmployeeProfileImg())
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_placeholder)
                    .into(binding.civProfileImg);
            // Bind other views and data as needed
        }

        private void bindEmpty() {
            // Handle empty state if needed
        }
    }
}
