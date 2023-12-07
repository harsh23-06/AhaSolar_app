package com.example.ahasolarapp.viewmodel

import com.example.ahasolarapp.api.ApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.repository.LeadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    private val _leadListLiveData = MutableLiveData<ApiResponse<LeadModel>>()

    val leadListLiveData = _leadListLiveData
    val leadData: LiveData<ApiResponse<LeadModel>> get() = _leadListLiveData

    fun getLeadList() {
        _leadListLiveData.value = ApiResponse.Loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val leadListRequest = LeadListRequest(search = "", page = 1, pageSize = 10)
                val response = repository.getLeadList(leadListRequest)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _leadListLiveData.postValue(ApiResponse.Success(responseBody))
                    } else {
                        _leadListLiveData.postValue(ApiResponse.Error("Response body is null"))
                    }
                } else {
                    _leadListLiveData.postValue(ApiResponse.Error("Error occurred"))
                }
            } catch (exception: Exception) {
                _leadListLiveData.postValue(ApiResponse.Error(exception.message ?: "Error occurred"))
            }
        }
    }
}
