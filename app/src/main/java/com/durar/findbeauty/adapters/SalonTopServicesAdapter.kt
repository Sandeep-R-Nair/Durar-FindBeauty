package com.durar.findbeauty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.durar.findbeauty.R
import com.durar.findbeauty.model.SalonDetails
import com.durar.findbeauty.model.SalonServiceGroup
import com.durar.findbeauty.utils.ApiConstants

class ServiceGroupAdapter(private var serviceGroups: List<SalonServiceGroup>) :
    RecyclerView.Adapter<ServiceGroupAdapter.ViewHolder>() {

    fun setData(newServiceGroups: List<SalonServiceGroup>) {
        serviceGroups = newServiceGroups
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_salon_top_service_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serviceGroup = serviceGroups[position]
        holder.serviceGroupName.text = serviceGroup.serviceGroupName
        Glide.with(holder.serviceGroupName.context)
            .load(ApiConstants.BASE_URL+serviceGroup.serviceGroupIcon)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(holder.ivServiceImg);
        // You can bind other data as needed
    }

    override fun getItemCount(): Int {
        return serviceGroups.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceGroupName: TextView = itemView.findViewById(R.id.tvServiceName)
        val ivServiceImg: ImageView = itemView.findViewById(R.id.ivServiceImg)
        // Add other views as needed
    }
}
