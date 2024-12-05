package com.example.demo2.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
object ApiClient {
    private const val BASE_URL_API="https://www.omdbapi.com/"
    // Create a logging interceptor to log request and response details
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY) // Logs request and response body
        return logging
    }

    // Create an OkHttpClient with the logging interceptor
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor()) // Add the logging interceptor
            .connectTimeout(30, TimeUnit.SECONDS) // Optional: Set timeout for connections
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_API)
            .client(getOkHttpClient()) // Use OkHttpClient with logging
            .build()
    }

    val instance: ApiService by lazy {
        retrofitService().create(ApiService::class.java)
    }
}