package com.example.ahasolarapp.api

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String?, val cause: Throwable? = null) : ApiResponse<Nothing>()
    data class Loading(val data: Nothing? = null) : ApiResponse<Nothing>()
}