package com.example.ahasolarapp.model

data class LeadListRequest(
    val search: String,
    val page: Int,
    val pageSize: Int
)
