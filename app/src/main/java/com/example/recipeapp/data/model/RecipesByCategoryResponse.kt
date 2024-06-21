package com.example.recipeapp.data.model

import com.google.gson.annotations.SerializedName

data class RecipesByCategoryResponse(
    @SerializedName("meals")
    val recipesByCategories: List<RecipesByCategory>
)
