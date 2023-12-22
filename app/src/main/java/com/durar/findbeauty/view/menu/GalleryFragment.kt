package com.durar.findbeauty.view.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.durar.findbeauty.adapters.GalleryAdapter
import com.durar.findbeauty.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView
        val recyclerView = binding.rvGallery
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        val adapter = GalleryAdapter()
        recyclerView.adapter = adapter

        // Sample data for the RecyclerView
        val itemList = mutableListOf<String>()
        for (i in 1..6) {
            itemList.add("Item $i")
        }

        // Set data to the adapter
        adapter.submitList(itemList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
