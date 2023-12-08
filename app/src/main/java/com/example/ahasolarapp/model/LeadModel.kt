package com.example.ahasolarapp.model

import Exceptions

data class LeadResponse(
    val status: Int,
    val statusCode: Int,
    val message: String,
    val data: LeadData,
    val exceptions: Any // You might want to create a data class for exceptions if needed
)

data class LeadData(
    val list: List<LeadModel>,
    val totalRecords: Int,
    val startIndex: Int,
    val endIndex: Int,
    val lastPage: Int
)

data class LeadModel(
    val leadCreatedBy: String,
    val leadId: Int,
    val projectName: String,
    val projectId: Int,
    val installerId: Int,
    val address: String,
    val updatedAt: String,
    val statusId: Int,
    val sourceLeadId: Int?,
    val statusName: String,
    val sourceLeadName: String?,
    val categoryName: String,
    val days: Int,
    val uniqueId: Any?, // Depending on the actual type
    val contractLoad: String,
    val capacityKw: String,
    val avgMonthlyBill: Int,
    val avgGenerate: Int,
    val loanRequired: Int,
    val existingCapacity: Any?, // Depending on the actual type
    val billDocument: Any?, // Depending on the actual type
    val branch: String,
    val createdAt: String,
    val pvCapacity: Any?, // Depending on the actual type
    val srNo: Int
)
