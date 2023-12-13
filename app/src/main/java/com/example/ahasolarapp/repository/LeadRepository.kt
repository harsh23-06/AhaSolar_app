package com.example.ahasolarapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.ahasolarapp.R
import com.example.ahasolarapp.api.ApiResponse
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.LoginRequest
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.model.VerifyData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeadRepository(private val apiService: ApiService) {


    fun getLeadList(
        url: String = "",
        apiRequest: JsonObject,
        authToken: String,
        apiResponse: MutableLiveData<List<LeadModel>>
    ) {


        val headers = "Bearer $authToken"
        apiService.getLeadListWithHeader(url, headers, apiRequest)
            .enqueue(object : Callback<LeadResponse> {
                override fun onResponse(
                    call: Call<LeadResponse>,
                    response: Response<LeadResponse>
                ) {
                    apiResponse.value = response.body()!!.data.list

                }

                override fun onFailure(call: Call<LeadResponse>, t: Throwable) {
                    apiResponse.value = null
                }


            })

    }
    fun sendOtp(
        url: String = "",
        apiRequest: JsonObject,
        apiResponse: MutableLiveData<LeadResponse>
    ) {

            apiService.sendOtpWithNoHeader(url, apiRequest).enqueue(object : Callback<LeadResponse>{
                override fun onResponse(
                    call: Call<LeadResponse>,
                    response: Response<LeadResponse>
                ) {
                    apiResponse.value = response.body()
                }

                override fun onFailure(call: Call<LeadResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            } )


    }
    /*suspend fun deleteLead(
        authToken: String,
        request: LeadDeleteRequest
    ): Response<LeadResponse> {
        val headers = mapOf("Authorization" to "Bearer $authToken")
        return apiService.deleteLead(headers, request)
    }

    suspend fun verifyOtp(request: OtpVerifyRequest): Response<VerifyData> {
        return apiService.verifyOtp(request)
    }

    suspend fun sendOtp(request: LoginRequest): Response<LeadResponse> {
        return apiService.sendOtp(request)
    }
*/
}


