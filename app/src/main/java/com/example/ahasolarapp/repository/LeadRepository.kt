package com.example.ahasolarapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.model.VerifyData
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
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

//    suspend fun verifyOtp(request: OtpVerifyRequest): Response<VerifyData<Any?>> {
//        return apiService.verifyOtp(request)
//    }

    fun verifyOtpApiRepo(url: String = "", apiRequest: JsonObject, apiResponse: MutableLiveData<String>) {
        apiService.postApiNoHeaders(url, apiRequest)
            .enqueue(object : Callback<VerifyData<Any>> {
                override fun onResponse(
                    call: Call<VerifyData<Any>>,
                    response: Response<VerifyData<Any>>
                ) {

                    if (response.body() != null) {
                        if (response.body()!!.status == 1) {
                            if (response.body()!!.statusCode == 200) {
                                if (response.body()!!.data != null) {
                                    apiResponse.value = response.body()!!.statusCode.toString()
                                } else {
                                    // Replace with appropriate function
                                    // context.toastMessage(response.body()!!.message.toString())
                                    apiResponse.value = null
                                }
                            } else if (response.body()!!.statusCode == 401) {
                                // Replace with appropriate function
                                // context.toastMessage(response.body()!!.message.toString())
                                apiResponse.value = null
                            }
                        } else {
                            // Replace with appropriate function
                            // context.toastMessage(response.body()!!.message.toString())
                        }
                    } else {
                        apiResponse.value = null
                    }
                }

                override fun onFailure(call: Call<VerifyData<Any>>, t: Throwable) {
                    // Replace dismissDialog() with appropriate function
                    // ...
                    apiResponse.value = null
                }
            })
    }

}


