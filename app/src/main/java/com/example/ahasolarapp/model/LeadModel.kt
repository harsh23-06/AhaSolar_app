package com.example.ahasolarapp.model

import com.google.gson.annotations.SerializedName

data class LeadModel(
    @SerializedName("leadCreatedBy") var leadCreatedBy: String? = null,
    @SerializedName("leadId") var leadId: Int? = null,
    @SerializedName("projectName") var projectName: String? = null,
    @SerializedName("projectId") var projectId: Int? = null,
    @SerializedName("installerId") var installerId: Int? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("statusId") var statusId: Int? = null,
    @SerializedName("sourceLeadId") var sourceLeadId: String? = null,
    @SerializedName("statusName") var statusName: String? = null,
    @SerializedName("sourceLeadName") var sourceLeadName: String? = null,
    @SerializedName("categoryName") var categoryName: String? = null,
    @SerializedName("days") var days: Int? = null,
    @SerializedName("uniqueId") var uniqueId: String? = null,
    @SerializedName("contractLoad") var contractLoad: String? = null,
    @SerializedName("capacityKw") var capacityKw: String? = null,
    @SerializedName("avgMonthlyBill") var avgMonthlyBill: Int? = null,
    @SerializedName("avgGenerate") var avgGenerate: Int? = null,
    @SerializedName("loanRequired") var loanRequired: Int? = null,
    @SerializedName("existingCapacity") var existingCapacity: String? = null,
    @SerializedName("billDocument") var billDocument: String? = null,
    @SerializedName("branch") var branch: String? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("pvCapacity") var pvCapacity: String? = null,
    @SerializedName("srNo") var srNo: Int? = null
)