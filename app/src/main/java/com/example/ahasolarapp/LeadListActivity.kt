    package com.example.ahasolarapp

    import LeadViewModel
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.View
    import androidx.appcompat.widget.SearchView
    import androidx.core.view.isVisible
    import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import com.example.ahasolarapp.adapter.LeadListAdapter
    import com.example.ahasolarapp.adapter.OnItemClickListener
    import com.example.ahasolarapp.api.ApiService
    import com.example.ahasolarapp.api.RetrofitInstance
    import com.example.ahasolarapp.databinding.ActivityLeadListBinding
    import com.example.ahasolarapp.model.LeadModel
    import com.example.ahasolarapp.model.LeadViewModelFactory
    import com.example.ahasolarapp.repository.LeadRepository

    class LeadListActivity : AppCompatActivity(), OnItemClickListener {
        private lateinit var binding: ActivityLeadListBinding
        private lateinit var adapter: LeadListAdapter
        private lateinit var leadViewsModel: LeadViewModel
        private var originalList: List<LeadModel> = mutableListOf()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLeadListBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.searchBar.isFocusable = true
            binding.noResFoundLL.isVisible = false

            val leadService = RetrofitInstance.retrofit.create(ApiService::class.java)
            val leadRepository = LeadRepository(leadService)

            leadViewsModel = ViewModelProvider(this, LeadViewModelFactory(leadRepository))[LeadViewModel::class.java]

            leadViewsModel.leadListLiveData.observe(this, Observer {

                originalList = it
                adapter.updateList(originalList)
                binding.noResFoundLL.isVisible = originalList.isEmpty()

            })

            val authToken =
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyMzYwNzc1LCJleHAiOjE3MDI1NTI3NzUsIm5iZiI6MTcwMjM2MDc3NSwianRpIjoiN2lvZDBEYU04TXZUR2c4cCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.wPij3OHL3UFFLypSUJzpKsLOihJoE9EhhGhaofjK1hc"
            leadViewsModel.getLeadList(authToken)

            binding.searchBarLL.setOnClickListener(setupSearchBar())

            setupRecyclerView()
        }

        private fun setupSearchBar(): View.OnClickListener {

            binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Handle search submission
                    return false
                }


                override fun onQueryTextChange(newText: String?): Boolean {
                    // Handle search text change
                    filter(newText!!)

                    return true
                }
            })
            return View.OnClickListener {
                binding.searchBar.isIconified = false
            }
        }

        private fun setupRecyclerView() {
            adapter = LeadListAdapter(this, originalList, this)
            binding.leadList.adapter = adapter
            binding.noResFoundLL.isVisible = originalList.isEmpty()

        }

        private fun filter(query: String) {
            val filteredList = originalList.filter { it.projectName.contains(query, ignoreCase = true) }
            adapter.updateList(filteredList)
            binding.noResFoundLL.isVisible = filteredList.isEmpty()

        }

        override fun onDeleteClick(position: Int) {
            val leadIdToDelete = leadViewsModel.leadListLiveData.value?.get(position)?.leadId
            Log.d("TAG", "onDeleteClick: $leadIdToDelete")
            if (leadIdToDelete != null) {
                val authToken =
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL3N0YWdpbmctYWhhc29sYXItcmV3YW1wLmFoYXNvbGFyLmluL2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyMzYwNzc1LCJleHAiOjE3MDI1NTI3NzUsIm5iZiI6MTcwMjM2MDc3NSwianRpIjoiN2lvZDBEYU04TXZUR2c4cCIsInN1YiI6IjIiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.wPij3OHL3UFFLypSUJzpKsLOihJoE9EhhGhaofjK1hc"
                leadViewsModel.deleteLead(authToken, leadIdToDelete)

            }
        }

    }