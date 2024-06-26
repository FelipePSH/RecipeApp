package com.example.recipeapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.model.RecipesByCategory
import com.example.recipeapp.data.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: CategoriesRepository
) : ViewModel() {

    private val _categoriesState = mutableStateOf(CategoriesState())
    val categoriesState: State<CategoriesState> = _categoriesState


    private val _selectedCategory = mutableStateOf(RecipeByCategoryState())
    val selectedCategoryState: State<RecipeByCategoryState> = _selectedCategory

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _categoriesState.value = CategoriesState(isLoading = true)
            try {
                _categoriesState.value = CategoriesState(
                    categories = repository.getCategories().categories,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    isLoading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }

    data class CategoriesState(
        val isLoading: Boolean = false,
        val categories: List<Category> = emptyList(),
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
