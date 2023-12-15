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

    }
}
