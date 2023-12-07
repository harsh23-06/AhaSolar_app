package com.example.ahasolarapp.api

import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/lead/getLeadList")
    suspend fun getLeadList(@Body request: LeadListRequest): Response<LeadModel>
}
object ApiServiceFactory {
    val apiService: ApiService = RetrofitInstance.retrofit.create(ApiService::class.java)
}
