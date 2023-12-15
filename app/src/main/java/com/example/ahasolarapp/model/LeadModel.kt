package com.example.ahasolarapp.model

import com.google.gson.annotations.SerializedName

data class LeadResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LeadData,
    @SerializedName("exceptions") val exceptions: Any
)

data class LeadData(
    @SerializedName("list") val list: List<LeadModel>,
    @SerializedName("totalRecords") val totalRecords: Int,
    @SerializedName("startIndex") val startIndex: Int,
    @SerializedName("endIndex") val endIndex: Int,
    @SerializedName("lastPage") val lastPage: Int
)

data class LeadModel(
    @SerializedName("leadCreatedBy") val leadCreatedBy: String,
    @SerializedName("leadId") val leadId: Int,
    @SerializedName("projectName") var projectName: String,
    @SerializedName("projectId") val projectId: Int,
    @SerializedName("installerId") val installerId: Int,
    @SerializedName("address") val address: String = "-",
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("statusId") val statusId: Int,
    @SerializedName("sourceLeadId") val sourceLeadId: Int?,
    @SerializedName("statusName") val statusName: String,
    @SerializedName("sourceLeadName") val sourceLeadName: String?,
    @SerializedName("categoryName") val categoryName: String?="",
    @SerializedName("days") val days: Int,
    @SerializedName("uniqueId") val uniqueId: Any?,
    @SerializedName("contractLoad") val contractLoad: String,
    @SerializedName("capacityKw") val capacityKw: String,
    @SerializedName("avgMonthlyBill") val avgMonthlyBill: Int,
    @SerializedName("avgGenerate") val avgGenerate: Int,
    @SerializedName("loanRequired") val loanRequired: Int,
    @SerializedName("existingCapacity") val existingCapacity: Any?,
    @SerializedName("billDocument") val billDocument: Any?,
    @SerializedName("branch") val branch: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("pvCapacity") val pvCapacity: Double,
    @SerializedName("srNo") val srNo: Int
)




data class OtpResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Any>,
    @SerializedName("exceptions") val exceptions: Map<String, Any>
)

data class VerifyData(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("mobile") var mobile: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("token") var token: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("users") var users: ArrayList<String> = arrayListOf(),
    @SerializedName("isMobileVerified") var isMobileVerified: Int? = null,
    @SerializedName("isEmailVerified") var isEmailVerified: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("isProfileCreated") var isProfileCreated: Boolean? = null,
    @SerializedName("isUserSelection") var isUserSelection: Boolean? = null,
    @SerializedName("isActive") var isActive: Int? = null
)
