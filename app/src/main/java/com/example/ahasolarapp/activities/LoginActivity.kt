package com.example.ahasolarapp.activities

import LeadViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLoginBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.model.LoginRequest
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
//        binding.otpLayout.error = null
        val apiInit = RetrofitInstance.getRetrofitClientObj(this@LoginActivity)

        val leadRepository = LeadRepository(apiInit.getApiInterface())
        leadViewsModel = ViewModelProvider(this, LeadViewModelFactory(leadRepository))[LeadViewModel::class.java]




        binding.loginButton.setOnClickListener {
            val phoneNumber = binding.phntxt.text.toString()

            leadViewsModel.sendOtp(phoneNumber)

            leadViewsModel.otpSend.observe(this, Observer {

                    binding.textView.text = it.message

            })
            val intent = Intent(this@LoginActivity,LeadListActivity::class.java)
            startActivity(intent)
            finish()

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