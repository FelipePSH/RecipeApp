package com.example.recipeapp.data.network

import MealResponse
import com.example.recipeapp.data.model.CategoriesResponse
import com.example.recipeapp.data.model.RecipesByCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse


    @GET("filter.php")
    suspend fun fetchRecipesByCategory(
        @Query("c") category: String
    ): RecipesByCategoryResponse


    @GET("lookup.php")
    suspend fun fetchRecipeById(
        @Query("i") id: String
    ) : MealResponse

}
