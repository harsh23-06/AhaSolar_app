import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.repository.LeadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    private val _leadListLiveData = MutableLiveData<List<LeadModel>>()
    val leadListLiveData: LiveData<List<LeadModel>> = _leadListLiveData

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
}
