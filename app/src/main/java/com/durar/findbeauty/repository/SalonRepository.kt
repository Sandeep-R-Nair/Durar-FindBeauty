package com.durar.findbeauty.repository

import com.durar.findbeauty.model.SalonResponse
import com.durar.findbeauty.network.ApiService
import retrofit2.Call

class SalonRepository(private val apiService: ApiService) {

    fun getSalonDetails(salonId: Int, lang: String): Call<SalonResponse> {
        return apiService.getSalonDetails(salonId, lang)
    }

}
