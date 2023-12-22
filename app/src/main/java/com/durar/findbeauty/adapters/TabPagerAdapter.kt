package com.durar.findbeauty.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.durar.findbeauty.view.menu.AboutFragment
import com.durar.findbeauty.view.menu.GalleryFragment

class TabPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2 // Number of tabs

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GalleryFragment()
            1 -> AboutFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}