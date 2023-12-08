package com.example.ahasolarapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.GetLeadModel
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeadRepository(private val apiService: ApiService) {

    val leads: MutableLiveData<GetLeadModel<LeadModel>> = MutableLiveData()

    fun getLeadList(token: String, request: LeadListRequest) {
        apiService.getLeadList(token, request).enqueue(object : Callback<GetLeadModel<LeadModel>> {
            override fun onResponse(call: Call<GetLeadModel<LeadModel>>, response: Response<GetLeadModel<LeadModel>>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    if (responseData != null) {
                        leads.value = responseData!!
                    }
                } else {
                    // Handle error case
                }
            }

            override fun onFailure(call: Call<GetLeadModel<LeadModel>>, t: Throwable) {
                // Handle failure case
            }
        })
    }

    // Add other methods as needed
}
