package com.example.ahasolarapp.activities

import LeadViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.R
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLeadListBinding
import com.example.ahasolarapp.databinding.ActivityLoginBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.repository.LeadRepository

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var leadViewsModel: LeadViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLayout.error = null
        binding.otpLayout.error = null

        leadViewsModel = ViewModelProvider(
            this,
            LeadViewModelFactory(LeadRepository(RetrofitInstance.retrofit.create(ApiService::class.java)))
        )[LeadViewModel::class.java]

        val loginBtn = binding.loginButton

        // Inside LoginActivity

//        val otpRequest = OtpVerifyRequest(mobile = "1111111111", otp = "123456")
//        leadViewsModel.verifyOtp(otpRequest)

        loginBtn.setOnClickListener{
            val phoneNumber = binding.phntxt.text.toString()
            val otp = binding.otptxt.text.toString()

            val otpRequest = OtpVerifyRequest(mobile = phoneNumber, otp = otp)
            leadViewsModel.verifyOtp(otpRequest)

        }


//        loginBtn.setOnClickListener {
//            val phoneNumber = binding.phntxt.text.toString()
//            val otp = binding.otptxt.text.toString()
//
//            if (phoneNumber == "1111111111") {
//                binding.phoneLayout.error = null
//                binding.otpLayout.error = null
//
//                if (otp == "123456") {
//                    binding.otpLayout.error = null
//
//                    val intent = Intent(this, LeadListActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    binding.otpLayout.error = "Provide Valid OTP"
//                }
//            } else {
//                binding.phoneLayout.error = "Provide Valid Phone Number"
//            }
//        }
    }
}