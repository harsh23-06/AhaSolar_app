import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ahasolarapp.repository.LeadRepository
import com.example.ahasolarapp.viewmodel.LeadViewModel

class LeadViewModelFactory(private val repository: LeadRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeadViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LeadViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
