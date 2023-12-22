package com.durar.findbeauty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.durar.findbeauty.repository.SalonRepository

class SalonViewModelFactory(private val repository: SalonRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SalonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
