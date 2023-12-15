import androidx.lifecycle.LiveData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.OtpResponse
import com.example.ahasolarapp.model.VerifyData
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.utils.Constants
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    val _leadListLiveData: MutableLiveData<List<LeadModel>> = MutableLiveData<List<LeadModel>>()
    val _deleteData: MutableLiveData<String> = MutableLiveData<String>()

    val otpSend: MutableLiveData<OtpResponse> = MutableLiveData()
    val verifyOtp: MutableLiveData<VerifyData> = MutableLiveData()
    private val _verifyOtpResult = MutableLiveData<Boolean>()
    val verifyOtpResult: LiveData<Boolean> get() = _verifyOtpResult



    fun getLeadList(authToken: String) {

        val apiRequest = JsonObject()
        apiRequest.addProperty("search", "")
        apiRequest.addProperty("page", 1)
        apiRequest.addProperty("pageSize", 10)
        repository.getLeadList(
            Constants.POST_GET_LEAD_LIST,
            apiRequest,
            authToken,
            _leadListLiveData
        )


    }

    fun sendOtp(phoneNumber: String, onOtpSent: () -> Unit) {
        val apiRequest = JsonObject()
        apiRequest.addProperty("emailOrMobile", phoneNumber)

        repository.sendOtp(Constants.POST_SEND_OTP, apiRequest, otpSend) {
            onOtpSent.invoke()
        }
    }


    fun verifyOtp(phoneNumber: String, otp: String, onOtpVerified: () -> Unit) {
        val apiRequest = JsonObject()
        apiRequest.addProperty("emailOrMobile", phoneNumber)
        apiRequest.addProperty("otp", otp)

        repository.verifyOtp(Constants.POST_VERIFY_OTP, apiRequest) {
            // Handle the response as needed
            onOtpVerified.invoke()
        }
    }




    fun deleteLead(authToken: String, leadId: Int) {

        val apiRequest = JsonObject()
        apiRequest.addProperty("actionType", 3)
        apiRequest.addProperty("leadId", leadId)
        repository.deleteLead(Constants.POST_DELETE_LIST_ITEM, apiRequest, authToken, _deleteData)

        /* viewModelScope.launch(Dispatchers.IO) {
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

fun verifyOtp(otpRequest: OtpVerifyRequest) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = repository.verifyOtp(otpRequest)
            if (response.isSuccessful) {
                // Handle successful OTP verification
                val verifyData = response.body()
                Log.e("OTP Verification", "Verified OTP")
            } else {
                // Handle error case
                Log.e("OTP Verification", "Error verifying OTP")
            }
        } catch (exception: Exception) {
            // Handle exception
            Log.e("OTP Verification", "Error verifying OTP", exception)
        }
    }
}
*/



