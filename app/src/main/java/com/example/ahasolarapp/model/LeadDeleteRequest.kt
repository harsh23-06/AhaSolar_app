package com.example.ahasolarapp.model

import retrofit2.http.Field

data class LeadDeleteRequest(
    val actionType: Int,
    val leadId: Int)
