package com.example.ahasolarapp.api

import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.LoginRequest
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.model.VerifyData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url


interface ApiService {

    @POST
     fun getLeadListWithHeader(
        @Url endpoint: String,
        @Header("Authorization") headers:String,
        @Body request: JsonObject
    ): Call<LeadResponse>

    @POST()
     fun deleteLeadWithHeader(
        @Url endpoint: String,
        @HeaderMap headers: Map<String, String>,
        @Body request: LeadDeleteRequest
    ): Call<LeadResponse>

    @POST()
    fun verifyOtpWithNoHeader(
        @Url endpoint: String,

        @Body request: OtpVerifyRequest
    ): Call<VerifyData>

    @POST()
     fun sendOtpWithNoHeader(
        @Url endpoint: String,

        @Body request: LoginRequest
    ): Call<LeadResponse>
}
