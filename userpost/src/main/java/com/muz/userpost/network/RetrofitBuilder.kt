package com.muz.userpost.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

  private fun getRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(BODY)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
    return Retrofit.Builder()
      .baseUrl("https://my-json-server.typicode.com/")
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  val userPostService: UserPostService = getRetrofit().create(UserPostService::class.java)
}