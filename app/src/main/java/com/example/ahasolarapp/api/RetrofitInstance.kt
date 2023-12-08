package com.example.ahasolarapp.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val BASE_URL = "https://staging-ahasolar-rewamp.ahasolar.in/"


    //this is used to directly parse the json data
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
