package com.example.ahasolarapp

import LeadViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.adapter.LeadListAdapter
import com.example.ahasolarapp.adapter.OnItemClickListener
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLeadListBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class LeadListActivity : AppCompatActivity(),OnItemClickListener{
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

        leadViewsModel.leadListLiveData.observe(this,Observer{

            val adapter = LeadListAdapter(this,it,this)
            binding.leadList.adapter = adapter

        })

        leadViewsModel.filteredLeadListLiveData.observe(this, Observer {
            val adapter = LeadListAdapter(this, it, this)
            binding.leadList.adapter = adapter
        })

        binding.searchBar.setOnSearchActionListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission if needed
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                leadViewsModel.filterLeads(newText.orEmpty())
                return true
            }
        })


        val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw"
        leadViewsModel.getLeadList(authToken)
    }

    override fun onDeleteClick(position: Int) {
        val leadIdToDelete = leadViewsModel.leadListLiveData.value?.get(position)?.leadId
        Log.d("TAG", "onDeleteClick: $leadIdToDelete")
        if (leadIdToDelete != null) {
            val authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw"
            leadViewsModel.deleteLead(authToken, leadIdToDelete)

        }
    }

}