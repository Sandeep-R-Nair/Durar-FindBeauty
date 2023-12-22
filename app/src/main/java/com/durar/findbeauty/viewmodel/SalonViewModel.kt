package com.durar.findbeauty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.durar.findbeauty.model.SalonResponse
import com.durar.findbeauty.repository.SalonRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SalonViewModel(private val repository: SalonRepository) : ViewModel() {

    private val _salonDetails = MutableLiveData<SalonResponse>()
    val salonDetails: LiveData<SalonResponse> get() = _salonDetails

    fun fetchSalonDetails(salonId: Int, lang: String) {
        repository.getSalonDetails(salonId, lang).enqueue(object : Callback<SalonResponse> {
            override fun onResponse(call: Call<SalonResponse>, response: Response<SalonResponse>) {
                if (response.isSuccessful) {
                    _salonDetails.value = response.body()
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<SalonResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
