package com.example.ahasolarapp.activities

import LeadViewModel
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.R
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.api.RetrofitInstance
import com.example.ahasolarapp.databinding.ActivityVerifyOtpBinding
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.repository.LeadRepository

class VerifyOtp : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private lateinit var leadViewModel: LeadViewModel
    private lateinit var phoneNumber: String
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val leadRepository = LeadRepository(leadService)
        leadViewModel = ViewModelProvider(this, LeadViewModelFactory(leadRepository))[LeadViewModel::class.java]

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        phoneNumber = intent.getStringExtra("phoneNumber") ?: ""

        val formattedMessage = formatPhoneNumber(phoneNumber)
        binding.message.text = "OTP sent to $formattedMessage"


        binding.verifyOtp.setOnClickListener {
            val enteredOtp = binding.otpTxt.text.toString()

            if (enteredOtp == "123456") {
                leadViewModel.verifyOtp(phoneNumber, enteredOtp) {
                    // Save login state in SharedPreferences
                    saveLoginState()

                    val intent = Intent(this, LeadListActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                binding.otpLayout.error = "Invalid OTP. Please enter a valid OTP."
            }
        }



        // Observe the result of OTP verification
//        leadViewModel.verifyOtpResult.observe(this, Observer { success ->
//            if (success) {
//                // Handle successful OTP verification, e.g., navigate to the lead list activity
//                val intent = Intent(this, LeadListActivity::class.java)
//                startActivity(intent)
//                finish() // Optional: Finish the current activity
//            } else {
//                // Handle unsuccessful OTP verification, e.g., display an error message
//                binding.otpLayout.error = "Invalid OTP. Please try again."
//            }
//        })
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        return "+91 ***${phoneNumber.takeLast(3)}"
    }
    private fun saveLoginState() {
        // Save login state as true
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
    }
}
