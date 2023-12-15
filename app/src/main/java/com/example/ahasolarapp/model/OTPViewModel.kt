//package com.example.ahasolarapp.model
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.ahasolarapp.repository.LeadRepository
//import com.example.ahasolarapp.utils.Constants
//import com.google.gson.JsonObject
//
//class OTPViewModel(private val repository: LeadRepository) : ViewModel() {
//
//    private val verifyOtpResponse: MutableLiveData<String> = MutableLiveData<String>()
//
//    fun verifyOtp(mobile: String, otp: String) {
//        val apiRequest: JsonObject = JsonObject()
//        apiRequest.addProperty("emailOrMobile", mobile)
//        repository.verifyOtpApiRepo(Constants.POST_VERIFY_OTP, apiRequest, verifyOtpResponse)
//    }
//}
