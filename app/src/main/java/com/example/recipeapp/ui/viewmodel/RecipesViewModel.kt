package com.example.recipeapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.PreparedRecipe
import com.example.recipeapp.data.model.RecipesByCategory
import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.domain.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {

    private val _recipeState = mutableStateOf(RecipeListState())
    val recipeState: State<RecipeListState> = _recipeState

    private val _selectedCategory = mutableStateOf(RecipeByCategoryState())
    val selectedCategoryState: State<RecipeByCategoryState> = _selectedCategory

//     fun fetchRecipeById(id: String){
//        viewModelScope.launch {
//            _recipeState.value = RecipeState(isLoading = true)
//            try {
//                _recipeState.value = RecipeState(
//                    isLoading = false,
//                    recipe = recipeUseCase.fetchRecipeById(id),
//                    error = null
//                )
//            } catch (e: Exception) {
//                _recipeState.value = RecipeState(
//                    isLoading = false,
//                    recipe = null,
//                    error = e.message
//                )
//            }
//        }
//    }


    fun fetchListOfRecipesByCategory(categoryName: String ) {
        viewModelScope.launch {
            _recipeState.value = RecipeListState(isLoading = true)
            try {
                val recipes = recipeUseCase.fetchListOfRecipesByCategory(categoryName)
                println("Fetched recipes: $recipes")
                _recipeState.value = RecipeListState(
                    isLoading = false,
                    recipe = recipes,
                    error = null
                )

            } catch (e: Exception) {
                _recipeState.value = RecipeListState(
                    isLoading = false,
                    recipe = listOf(),
                    error = e.message
                )
            }
        }
    }

    data class RecipeListState(
        val isLoading: Boolean = false,
        val recipe: List<PreparedRecipe> = emptyList(),
        val error: String? = null
    )


    fun fetchRecipesByCategory(categoryName: String) {
        viewModelScope.launch {
            _selectedCategory.value = RecipeByCategoryState(isLoading = true)
            try {
                _selectedCategory.value = RecipeByCategoryState(
                    recipes = repository.getRecipesByCategory(categoryName).recipesByCategories,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _selectedCategory.value = _selectedCategory.value.copy(
                    isLoading = false,
                    error = "Error fetching recipes by category ${e.message}"
                )
            }
        }
    }


    data class RecipeByCategoryState(
        val isLoading: Boolean = false,
        val recipes: List<RecipesByCategory> = emptyList(),
        val error: String? = null
    )


}