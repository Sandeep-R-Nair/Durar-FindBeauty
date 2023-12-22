package com.durar.findbeauty.adapters

// GalleryAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.durar.findbeauty.R

class GalleryAdapter : ListAdapter<String, RecyclerView.ViewHolder>(GalleryDiffCallback()) {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_BUTTON = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_ITEM -> GalleryItemViewHolder(inflater.inflate(R.layout.adapter_item_gallery, parent, false))
            VIEW_TYPE_BUTTON -> GalleryButtonViewHolder(inflater.inflate(R.layout.item_gallery_button, parent, false))
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_ITEM -> (holder as GalleryItemViewHolder).bind(getItem(position))
            VIEW_TYPE_BUTTON -> (holder as GalleryButtonViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) VIEW_TYPE_BUTTON else VIEW_TYPE_ITEM
    }

    // ViewHolder for normal items
    class GalleryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageGalleryItem)
        private val tvGallery: TextView = itemView.findViewById(R.id.textGalleryItem)

        fun bind(item: String) {
            // Bind your data to the views
            tvGallery.text = item

        }
    }

    // ViewHolder for the "View More" button
    class GalleryButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.buttonViewMore)

        fun bind() {
            // Implement the button click logic here
            button.setOnClickListener {
                // Handle the "View More" button click
            }
        }
    }
}

class GalleryDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
