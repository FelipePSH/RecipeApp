package com.example.recipeapp.data.repository

import MealResponse
import com.example.recipeapp.data.network.ApiService
import javax.inject.Inject

class RecipeRepository  @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchRecipeById(id: String): MealResponse = apiService.fetchRecipeById(id)


    suspend fun getRecipesByCategory(category: String) = apiService.fetchRecipesByCategory(category)

}



