package com.durar.findbeauty.network

import com.durar.findbeauty.model.SalonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/user/get-salon-details")
    fun getSalonDetails(
        @Query("SalonId") salonId: Int,
        @Query("Lang") lang: String
    ): Call<SalonResponse>

}