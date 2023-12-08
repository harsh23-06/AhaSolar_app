package com.example.ahasolarapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ahasolarapp.model.GetLeadModel
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.repository.LeadRepository

class LeadViewModel(private val repository: LeadRepository, private val token: String) : ViewModel() {

    val leads: LiveData<GetLeadModel<LeadModel>> = repository.leads

    fun getLeadList(request: LeadListRequest) {
        repository.getLeadList(token, request)
    }

}
