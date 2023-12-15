package com.example.ahasolarapp.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.LoginRequest
import com.example.ahasolarapp.model.OtpResponse
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.model.VerifyData
import com.google.gson.JsonElement
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
        apiResponse: MutableLiveData<OtpResponse>,
        context: Context
    ) {

        apiService.sendOtpWithNoHeader(url, apiRequest).enqueue(object : Callback<OtpResponse> {
            override fun onResponse(
                call: Call<OtpResponse>,
                response: Response<OtpResponse>
            ) {
                apiResponse.value = response.body()
                Toast.makeText(context.applicationContext,response.body()!!.message,Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
    fun deleteLead(
        url: String = "",
        apiRequest: JsonObject,
        authToken: String,
        apiResponse: MutableLiveData<String>
    ){
        val headers = "Bearer $authToken"

        apiService.deleteLeadWithHeader(url,headers,apiRequest).enqueue(object : Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                apiResponse.value = "200"
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                apiResponse.value = null
            }

        })
    }

/*
    suspend fun verifyOtp(request: OtpVerifyRequest): Response<VerifyData> {
        return apiService.verifyOtp(request)
    }
*/



}


