package com.example.ahasolarapp.model

import com.google.gson.annotations.SerializedName


data class LeadResponse(
    val status: Int,
    val statusCode: Int,
    val message: String,
    val data: LeadData,
    val exceptions: Any
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
    var projectName: String,
    val projectId: Int,
    val installerId: Int,
    val address: String = "-",
    val updatedAt: String,
    val statusId: Int,
    val sourceLeadId: Int?,
    val statusName: String,
    val sourceLeadName: String?,
    val categoryName: String,
    val days: Int,
    val uniqueId: Any?,
    val contractLoad: String,
    val capacityKw: String,
    val avgMonthlyBill: Int,
    val avgGenerate: Int,
    val loanRequired: Int,
    val existingCapacity: Any?,
    val billDocument: Any?,
    val branch: String,
    val createdAt: String,
    val pvCapacity: Double,
    val srNo: Int
)

data class DeleteLeadRequest(
    val actionType: Int,
    val leadId: Int
)

data class OtpResponse(
    val status: Int,
    val statusCode: Int,
    val message: String,
    val data: List<Any>,
    val exceptions: Map<String, Any>
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



