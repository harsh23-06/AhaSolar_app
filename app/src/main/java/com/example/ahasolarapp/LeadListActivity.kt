package com.example.ahasolarapp

import LeadViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.adapter.LeadListAdapter
import com.example.ahasolarapp.api.ApiResponse
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.ApiServiceFactory
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLeadListBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class LeadListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeadListBinding

    private lateinit var leadViewsModel: LeadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeadListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val leadRepository = LeadRepository(ApiServiceFactory.apiService)

        leadViewsModel = ViewModelProvider(this,LeadViewModelFactory(leadRepository)).get(
            LeadViewModel::class.java
        )

        leadViewsModel.leadListLiveData.observe(this, Observer {apiResponse ->
            when (apiResponse) {
                is ApiResponse.Loading -> {
                    // Handle loading state if needed
                }
                is ApiResponse.Success -> {
                    // Access the list of LeadModel from the ApiResponse.Success
                    val leadList = apiResponse.data
                    val leadsListAdapter = LeadListAdapter(this, leadList)
                    // Set the adapter to your RecyclerView
                    binding.leadList.adapter = leadsListAdapter
                }
                is ApiResponse.Error -> {
                    // Handle error state if needed
                    val errorMessage = apiResponse.message
                    // Show an error message to the user
                }
            }
        })
        leadViewsModel.getLeadList()
    }
}