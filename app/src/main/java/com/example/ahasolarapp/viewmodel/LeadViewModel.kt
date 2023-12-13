import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.DeleteLeadRequest
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadViewModelFactory
import com.example.ahasolarapp.model.OTPViewModel
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.utils.Constants
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    private val _leadListLiveData = MutableLiveData<List<LeadModel>>()
    val leadListLiveData: LiveData<List<LeadModel>> = _leadListLiveData
    private val loginWithMobileResponse: MutableLiveData<String> = MutableLiveData<String>()
    val otpViewModel: OTPViewModel by lazy {
        ViewModelProvider(this, LeadViewModelFactory(repository)).get(OTPViewModel::class.java)
    }




    fun getLeadList(authToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val leadListRequest = LeadListRequest(search = "", page = 1, pageSize = 10)
                val response = repository.getLeadList(authToken, leadListRequest)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _leadListLiveData.postValue(responseBody.data.list)
                    } else {
                        Log.d("Error in response body", "getLeadList: Error")
                    }
                }

            } catch (exception: Exception) {
                Log.d("Error in response body", "getLeadList: Error")
            }
        }
    }




    fun deleteLead(authToken: String, leadId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deleteRequest = LeadDeleteRequest(actionType = 3, leadId = leadId)
                Log.d("TAG", "deleteLead: $deleteRequest")
                val response = repository.deleteLead(authToken, deleteRequest)
                if (response.isSuccessful) {
                    getLeadList(authToken)
                    Log.d("Lead Deletion", "Successfully deleted lead with ID: $leadId")
                } else {
                    // Handle error case
                    Log.e("", "Error deleting lead with ID: $leadId")
                }

                // Reload lead list after deletion
                getLeadList(authToken)
            } catch (exception: Exception) {
                // Handle exception
                Log.e("Lead Deletion", "Error deleting lead with ID: $leadId", exception)
            }
        }
    }

//    fun verifyOtp(mobile: String, otp: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val otpVerifyRequest = OtpVerifyRequest(mobile = "1111111111", otp = "123456")
//                val response = repository.verifyOtp(otpVerifyRequest)
//                if (response.isSuccessful) {
//                    // Handle successful OTP verification
//                    val verifyData = response.body()
//                    Log.e("OTP Verification", "Verified OTP")
//                } else {
//                    // Handle error case
//                    Log.e("OTP Verification", "Error verifying OTP")
//                }
//            } catch (exception: Exception) {
//                // Handle exception
//                Log.e("OTP Verification", "Error verifying OTP", exception)
//            }
//        }
//    }


    fun loginWithMobile() {
        val apiRequest: JsonObject = JsonObject()
        apiRequest.addProperty("emailOrMobile", mobile.get().toString())

        repository!!.loginWithMobileApiRepo(
            isLoaderRequired = true,
            Constants.POST_SEND_OTP,
            apiRequest,
            loginWithMobileResponse
        )

        // Call OTP verification after sending OTP
        otpViewModel.verifyOtp(mobile.get().toString(), "123456")
    }

}
