package com.example.ahasolarapp

import LeadViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.adapter.LeadListAdapter
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLeadListBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class LeadListActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLeadListBinding

    private lateinit var leadViewsModel: LeadViewModel
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityLeadListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val leadRepository = LeadRepository(leadService)

        leadViewsModel = ViewModelProvider(this,LeadViewModelFactory(leadRepository)).get(
            LeadViewModel::class.java
        )

//        leadViewsModel = ViewModelProvider(this).get(LeadViewModel::class.java)
        leadViewsModel.leadListLiveData.observe(this,Observer{

//            binding.tvText.text = it[0].address
//            Log.d("Dataaaaaaaa", "onCreate: ${it.toString()}")
            val adapter = LeadListAdapter(this,it)
            binding.leadList.adapter = adapter

        })

        val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw"
        leadViewsModel.getLeadList(authToken)
    }

}