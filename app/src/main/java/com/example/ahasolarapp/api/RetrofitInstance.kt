package com.example.ahasolarapp.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

import retrofit2.create
import java.util.concurrent.TimeUnit


class RetrofitInstance(application: Context) {


    private lateinit var httpClient: OkHttpClient

    private lateinit var apiInterface: ApiService
    var gson = GsonBuilder()
        .setLenient()
        .create()

    init {
        buildHttpClient(application)
    }

    companion object {
        private lateinit var retrofitClient: RetrofitInstance

        fun getRetrofitClientObj(application: Context): RetrofitInstance {
            //  if (!Companion::retrofitClient.isInitialized) {
            retrofitClient = RetrofitInstance(application)
            //   }
            return retrofitClient
        }
    }

    private fun buildHttpClient(application: Context) {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val loggingInterceptor =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        builder.addInterceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            val request: Request = requestBuilder.build()
            it.proceed(request)
        }
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)
        httpClient = builder.build()
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }


    fun getApiInterface(): ApiService {
        if (!::apiInterface.isInitialized) {
            apiInterface =
                getRetrofit("https://staging-ahasolar-rewamp.ahasolar.in/api/").create(ApiService::class.java)
        }
        return apiInterface
    }
}
