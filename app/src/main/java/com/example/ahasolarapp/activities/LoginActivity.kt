package com.example.ahasolarapp.activities

import LeadViewModel
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityLoginBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var leadViewsModel: LeadViewModel
    private var phoneNumber: String = ""
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLayout.error = null

        val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val leadRepository = LeadRepository(leadService)
        leadViewsModel = ViewModelProvider(this, LeadViewModelFactory(leadRepository))[LeadViewModel::class.java]

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // Check if the user is already logged in
        if (isLoggedIn()) {
            // If logged in, redirect to the LeadListActivity
            val intent = Intent(this, LeadListActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        binding.sendOtpButton.setOnClickListener {
            val phoneNumber = binding.phntxt.text.toString()

            if (phoneNumber == "1111111111") {
                binding.phoneLayout.error = null

                // Hardcoded OTP for testing purposes
                val correctOtp = "123456"

                leadViewsModel.sendOtp(phoneNumber) {
                    Log.d("SendOtpCallback", "OTP Sent callback executed")
                    // Save phone number in SharedPreferences
                    savePhoneNumber(phoneNumber)

                    val intent = Intent(this, VerifyOtp::class.java)
                    intent.putExtra("phoneNumber", phoneNumber)
                    startActivity(intent)

                }
            } else {
                binding.phoneLayout.error = "Provide Valid Phone Number"
            }
        }
// Observer for otpSend
        leadViewsModel.otpSend.observe(this, Observer {
            val phoneNumber = binding.phntxt.text.toString()
            binding.textView.text = it.message

            // Open VerifyOtp activity
            val intent = Intent(this, VerifyOtp::class.java)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        })
    }

    private fun isLoggedIn(): Boolean {
        // Retrieve login state from SharedPreferences
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun savePhoneNumber(phoneNumber: String) {
        // Save phone number in SharedPreferences
        sharedPreferences.edit().putString("phoneNumber", phoneNumber).apply()
    }
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
//    }
//}