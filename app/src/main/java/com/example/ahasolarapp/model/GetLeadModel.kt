package com.example.ahasolarapp.model

import com.google.gson.annotations.SerializedName

data class GetLeadModel<T>(
    @SerializedName("list") var leadList: ArrayList<T> = arrayListOf(),
    @SerializedName("totalRecords") var totalRecords: Int? = null,
    @SerializedName("startIndex") var startIndex: Int? = null,
    @SerializedName("endIndex") var endIndex: Int? = null,
    @SerializedName("lastPage") var lastPage: Int? = null
)



