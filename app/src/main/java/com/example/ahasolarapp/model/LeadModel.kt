package com.example.ahasolarapp.model

import Exceptions

data class LeadModel(
    val exceptions: Exceptions,
    val message: String?,
    val status: Int,
    val statusCode: Int,
    val address: String?,
    val avgGenerate: Int,
    val avgMonthlyBill: Int,
    val billDocument: Any,
    val branch: String?,
    val capacityKw: String?,
    val categoryName: String?,
    val contractLoad: String?,
    val createdAt: String?,
    val days: Int,
    val existingCapacity: Any,
    val installerId: Int,
    val leadCreatedBy: String?,
    val leadId: Int,
    val loanRequired: Int,
    val projectId: Int,
    val projectName: String?,
    val pvCapacity: Any,
    val sourceLeadId: Int,
    val sourceLeadName: String?,
    val srNo: Int,
    val statusId: Int,
    val statusName: String?,
    val uniqueId: Any,
    val updatedAt: String?
)