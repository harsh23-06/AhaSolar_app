package com.example.ahasolarapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.OtpResponse
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
        apiResponse: MutableLiveData<OtpResponse>,
        function: () -> Unit
    ) {

        apiService.sendOtpWithNoHeader(url, apiRequest).enqueue(object : Callback<OtpResponse> {
            override fun onResponse(
                call: Call<OtpResponse>,
                response: Response<OtpResponse>
            ) {
                apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun verifyOtp(url: String = "", apiRequest: JsonObject, onComplete: (Boolean) -> Unit) {
        apiService.verifyOtpWithNoHeader(url, apiRequest).enqueue(object : Callback<VerifyData> {
            override fun onResponse(call: Call<VerifyData>, response: Response<VerifyData>) {
                // Handle the response as needed
                val success = response.isSuccessful // Adjust this based on your API response
                onComplete.invoke(success)
            }

            override fun onFailure(call: Call<VerifyData>, t: Throwable) {
                // Handle failure
                onComplete.invoke(false)
            }
        })
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


*/
}


