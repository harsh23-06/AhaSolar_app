package com.example.ahasolarapp.api

import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.VerifyData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url


interface ApiService {

    @POST("api/lead/getLeadList")
    suspend fun getLeadList(
        @HeaderMap headers: Map<String, String>,
        @Body request: LeadListRequest
    ): Response<LeadResponse>
    @POST("api/lead/leadCreate")
    suspend fun deleteLead(
        @HeaderMap headers: Map<String, String>,
        @Body request: LeadDeleteRequest
    ): Response<LeadResponse>

//    @POST("api/auth/verifyOtp")
//    suspend fun verifyOtp(
//        @Body request: OtpVerifyRequest
//    ): Response<VerifyData>

    @POST
    fun postApiNoHeaders(
        @Url endPoint: String,
        @Body params: JsonObject
    ): Call<VerifyData<Any>>

}
