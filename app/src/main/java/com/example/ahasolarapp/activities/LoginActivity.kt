package com.example.ahasolarapp.activities

import LeadViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.api.ApiResponse
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLoginBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var leadViewsModel: LeadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLayout.error = null
//        binding.otpLayout.error = null

//        val loginBtn = binding.loginButton
        val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val leadRepository = LeadRepository(leadService)

        leadViewsModel = ViewModelProvider(this, LeadViewModelFactory(leadRepository)).get(
            LeadViewModel::class.java
        )
        /*loginBtn.setOnClickListener {
            val phoneNumber = binding.phntxt.text.toString()
            val otp = binding.otptxt.text.toString()

            if (phoneNumber == "1111111111") {
                binding.phoneLayout.error = null
                binding.otpLayout.error = null

                if (otp == "123456") {
                    binding.otpLayout.error = null

                    val intent = Intent(this, LeadListActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.otpLayout.error = "Provide Valid OTP"
                }
            } else {
                binding.phoneLayout.error = "Provide Valid Phone Number"
            }
        }*/

        leadViewsModel.otpResponse.observe(this, Observer { response ->
            if (response.statusCode == 200) {
                Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                Log.d("response", "onCreate: response code ${response.message}")

                binding.textView.text = response.message
            } else
                Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()

        }
        )
        binding.loginButton.setOnClickListener {
            onSendOtpButtonClick()


        }


    }

    private fun onSendOtpButtonClick() {
        val phoneNumber = "1111111111"
        val authToken =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyMjczMDI2LCJleHAiOjE3MDI0NjUwMjYsIm5iZiI6MTcwMjI3MzAyNiwianRpIjoiUEhQZ2ZyY1diZzBWTzNvYSIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.w7cTUcgaFz7Hz5F52WdkyW1VF8SQt3Kr1ONWRnRdYNk"

        leadViewsModel.sendOtp(phoneNumber)
    }
}