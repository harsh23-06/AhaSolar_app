package com.example.ahasolarapp.repository

import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.LoginRequest
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class LeadRepository(private val apiService: ApiService) {


    suspend fun getLeadList(authToken: String, request: LeadListRequest): Response<LeadResponse> {
        val headers = mapOf("Authorization" to "Bearer $authToken")
        return apiService.getLeadList(headers, request)
    }

    suspend fun deleteLead(authToken: String, request: LeadDeleteRequest): Response<LeadResponse> {
        val headers = mapOf("Authorization" to "Bearer $authToken")
        return apiService.deleteLead(headers, request)
    }

    fun sendOtp(loginRequest: LoginRequest): Response<LeadResponse> {
//        val headers = mapOf("Authorization" to "Bearer $authToken")

        return apiService.sendOtp(loginRequest)
    }
}


