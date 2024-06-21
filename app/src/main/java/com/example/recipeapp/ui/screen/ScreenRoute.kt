package com.example.recipeapp.ui.screen

sealed class ScreenRoute(val route: String) {
    object Home : ScreenRoute("home")
    object RecipesByCategory : ScreenRoute("recipesbycategoryscreen")
}