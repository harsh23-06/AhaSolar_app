package com.example.ahasolarapp

import LeadViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.api.ApiResponse
import com.example.ahasolarapp.api.ApiServiceFactory
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.viewmodel.LeadViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LeadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = LeadRepository(ApiServiceFactory.apiService)
        viewModel = ViewModelProvider(this, LeadViewModelFactory(repository))[LeadViewModel::class.java]

        // Observe the LiveData
        viewModel.leadData.observe(this, Observer { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val message = response.data.message
                    updateTextView("Message from LeadModel: $message")
                }

                is ApiResponse.Error -> {
                    val errorMessage = response.message
                    updateTextView("Error: $errorMessage")
                }

                is ApiResponse.Loading -> {
                    updateTextView("Loading...")
                }
            }
        })

        // Trigger the API call
        viewModel.getLeadList()
    }

    private fun updateTextView(text: String) {
        runOnUiThread {
            findViewById<TextView>(R.id.dataTextView).text = text
        }
    }
}
