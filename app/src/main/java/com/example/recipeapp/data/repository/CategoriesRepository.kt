package com.example.recipeapp.data.repository

import com.example.recipeapp.data.network.ApiService
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCategories() = apiService.getCategories()


    suspend fun getRecipesByCategory(category: String) = apiService.getRecipesByCategory(category)

}