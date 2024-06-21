package com.example.recipeapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.viewmodel.CategoriesViewModel

@Composable
fun RecipeAppMain(
    navController: NavHostController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
) {
    val viewState by categoriesViewModel.categoriesState
    val selectedCategoryViewState by categoriesViewModel.selectedCategoryState

    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(route = ScreenRoute.Home.route) {
            HomeScreen(navigateToRecipesByCategory = {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(ScreenRoute.RecipesByCategory.route)
            }, viewState = viewState)
        }
        composable(route = ScreenRoute.RecipesByCategory.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")
            RecipesByCategoryScreen(category = category )
        }
    }

}