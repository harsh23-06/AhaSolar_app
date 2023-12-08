package com.example.ahasolarapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.api.ApiServiceFactory
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.viewmodel.LeadViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LeadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = LeadRepository(ApiServiceFactory.apiService)
        val token = getHeaderToken()

        viewModel = ViewModelProvider(this,
            LeadViewModelFactory(repository, token))[LeadViewModel::class.java]


        val request = LeadListRequest(search = "", page = 1, pageSize = 10)

        viewModel.leads.observe(this, Observer { data ->
            if (data != null) {
                if (data.leadList.isNotEmpty()) {
                    val stringBuilder = StringBuilder()
                    for (lead in data.leadList) {
                        val address = lead.address ?: "No address available"
                        stringBuilder.append("Message:\nAddress: $address\n\n")
                    }
                    updateTextView(stringBuilder.toString())
                } else {
                    updateTextView("Lead list is empty")
                }
            } else {
                updateTextView("Data is null")
            }
        })

        viewModel.getLeadList(request)
    }

    private fun getHeaderToken(): String {
        // Return your header token
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAxOTQ1NDY4LCJleHAiOjE3MDIxMzc0NjgsIm5iZiI6MTcwMTk0NTQ2OCwianRpIjoiUG9lV2U0ZVBSdElXS1Q3TCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.N73t5AvksTXJYV_FL0s3OIVQf27lq5JmwFIEquNg9sw"
    }

    private fun updateTextView(text: String) {
        runOnUiThread {
            val textView = findViewById<TextView>(R.id.dataTextView)
            textView.text = text
        }
    }
}
