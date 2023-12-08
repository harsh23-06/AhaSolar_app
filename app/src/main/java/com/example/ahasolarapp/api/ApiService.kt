package com.example.ahasolarapp.api

import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT


interface ApiService {

    @POST("api/lead/getLeadList")
    suspend fun getLeadList(
        @HeaderMap headers: Map<String, String>,
        @Body request: LeadListRequest
    ): Response<LeadResponse>
}
