package com.example.recipeapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val API_KEY = "9973533/"

    private const val BASE_URL = "https://www.themealdb.com/api/json/v2/$API_KEY"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}