package com.example.ahasolarapp.repository

import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.api.ApiService
import retrofit2.Response

class LeadRepository(private val apiService: ApiService) {

    suspend fun getLeadList(request: LeadListRequest): Response<LeadModel> {
        return apiService.getLeadList(request)
    }
}
