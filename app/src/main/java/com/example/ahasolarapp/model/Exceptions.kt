package com.example.ahasolarapp.model

import com.google.gson.annotations.SerializedName

data class Exceptions(
    @SerializedName("property1") val property1: String,
    @SerializedName("property2") val property2: Int
)
