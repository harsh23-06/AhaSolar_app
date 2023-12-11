import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahasolarapp.model.DeleteLeadRequest
import com.example.ahasolarapp.model.LeadListRequest
import com.example.ahasolarapp.model.LeadModel
import com.example.ahasolarapp.repository.LeadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LeadViewModel(private val repository: LeadRepository) : ViewModel() {

    private val _leadListLiveData = MutableLiveData<List<LeadModel>>()
    val leadListLiveData: LiveData<List<LeadModel>> = _leadListLiveData
    private val _filteredLeadListLiveData = MutableLiveData<List<LeadModel>>()
    val filteredLeadListLiveData: LiveData<List<LeadModel>> = _filteredLeadListLiveData


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


    fun filterLeads(query: String) {
        val filteredList = _leadListLiveData.value
            ?.filter { it.projectName.contains(query, ignoreCase = true) }
            ?: emptyList()

        _filteredLeadListLiveData.postValue(filteredList)
    }




    fun deleteLead(authToken: String, leadId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deleteRequest = DeleteLeadRequest(actionType = 3, leadId = leadId)
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

}
