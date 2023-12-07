package com.example.ahasolarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahasolarapp.adapter.LeadListAdapter
import com.example.ahasolarapp.databinding.ActivityLeadListBinding

class LeadListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeadListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeadListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}