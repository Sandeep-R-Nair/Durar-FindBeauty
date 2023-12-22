package com.durar.findbeauty.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.durar.findbeauty.R;
import com.durar.findbeauty.databinding.AdapterTopServiceItemBinding;
import com.durar.findbeauty.model.TopServiceData;

import java.util.List;


public class TopServicesAdapter extends RecyclerView.Adapter<TopServicesAdapter.ViewHolder> {

    private List<TopServiceData> topServicesList;
    private int month= 1;

    public TopServicesAdapter(List<TopServiceData> topServicesList, int month) {
        this.topServicesList = topServicesList;
        this.month = month;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use View Binding to inflate the layout
        AdapterTopServiceItemBinding binding = AdapterTopServiceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopServiceData serviceData = topServicesList.get(position);

        // Bind data to the ViewHolder using View Binding
        holder.binding.tvServiceName.setText(serviceData.getService_Name());
        if(month==1) {
            holder.binding.tvCount.setText(String.valueOf(serviceData.getThisMonth()));
        }else{
            holder.binding.tvCount.setText(String.valueOf(serviceData.getLastMonth()));
        }

        // Set background color alternatively
        int backgroundColor = (position % 2 == 0) ? ContextCompat.getColor(holder.itemView.getContext(), R.color.top_service_grey_bg)
                : ContextCompat.getColor(holder.itemView.getContext(), R.color.white);
        holder.binding.mainLayout.setBackgroundColor(backgroundColor);    }

    @Override
    public int getItemCount() {
        return topServicesList.size();
    }

    public void updateData(List<TopServiceData> newList) {
        topServicesList.clear();
        topServicesList.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Use the generated View Binding class
        private final AdapterTopServiceItemBinding binding;

        public ViewHolder(@NonNull AdapterTopServiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}






