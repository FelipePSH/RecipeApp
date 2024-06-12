package com.example.recipeapp.data.network

import com.example.recipeapp.data.model.CategoriesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

}