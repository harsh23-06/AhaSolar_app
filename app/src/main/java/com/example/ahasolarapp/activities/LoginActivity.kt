package com.example.ahasolarapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahasolarapp.LeadListActivity
import com.example.ahasolarapp.R
import com.example.ahasolarapp.databinding.ActivityLeadListBinding
import com.example.ahasolarapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLayout.error = null
        binding.otpLayout.error = null

        val loginBtn = binding.loginButton

        loginBtn.setOnClickListener {
            val phoneNumber = binding.phntxt.text.toString()
            val otp = binding.otptxt.text.toString()

            if (phoneNumber == "1111111111") {
                binding.phoneLayout.error = null
                binding.otpLayout.error = null

                if (otp == "123456") {
                    binding.otpLayout.error = null

                    val intent = Intent(this, LeadListActivity::class.java)
                    startActivity(intent)
                } else {
                    binding.otpLayout.error = "Provide Valid OTP"
                }
            } else {
                binding.phoneLayout.error = "Provide Valid Phone Number"
            }
        }
    }
}