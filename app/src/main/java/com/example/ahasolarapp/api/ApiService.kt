package com.example.ahasolarapp.api

import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface ApiService {

    @PUT("api/lead/getLeadList")
    suspend fun getLeadList(@Body request: LeadListRequest): Response<LeadModel>
}
