package com.example.ahasolarapp

import LeadViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
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
import com.example.ahasolarapp.utils.Constants

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

        val apiInit = RetrofitInstance.getRetrofitClientObj(this@LeadListActivity)

        val leadRepository = LeadRepository(apiInit.getApiInterface())

        leadViewsModel =
            ViewModelProvider(this, LeadViewModelFactory(leadRepository))[LeadViewModel::class.java]

        leadViewsModel._leadListLiveData.observe(this, Observer {
            originalList = it
            adapter.updateList(originalList)
            binding.noResFoundLL.isVisible = originalList.isEmpty()
        })
        leadViewsModel._deleteData.observe(this, Observer {
            if (it != null) {
                if (it.equals("200")) {
                    leadViewsModel.getLeadList(Constants.auth)
                }
            }

        })
        leadViewsModel.getLeadList(Constants.auth)

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

    override fun onDeleteClick(position: LeadModel) {

        leadViewsModel.deleteLead(Constants.auth, position.leadId)
    }

}