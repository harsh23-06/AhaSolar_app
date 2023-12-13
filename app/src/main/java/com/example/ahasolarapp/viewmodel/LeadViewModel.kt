import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahasolarapp.api.ApiService
import com.example.ahasolarapp.model.DeleteLeadRequest
import com.example.ahasolarapp.model.LeadDeleteRequest
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.model.LeadResponse
import com.example.ahasolarapp.model.LoginRequest
import com.example.ahasolarapp.model.OtpVerifyRequest
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.utils.Constants
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    val _leadListLiveData: MutableLiveData<List<LeadModel>> = MutableLiveData<List<LeadModel>>()

    //    val leadListLiveData: LiveData<List<LeadModel>> = _leadListLiveData
    private val _filteredLeadListLiveData = MutableLiveData<List<LeadModel>>()
    val otpSend: MutableLiveData<LeadResponse> = MutableLiveData()


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

    fun sendOtp(phoneNumber: String) {
        val apiRequest = JsonObject()
        apiRequest.addProperty("emailOrMobile", phoneNumber)
        repository.sendOtp(Constants.POST_SEND_OTP, apiRequest, otpSend)

    }

}


/*fun deleteLead(authToken: String, leadId: Int) {
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
}*/



