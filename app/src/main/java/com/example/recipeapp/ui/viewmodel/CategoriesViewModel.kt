package com.example.recipeapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: CategoriesRepository) : ViewModel() {

    val _categorieState = mutableStateOf(CategoriesState())
    val categoriesState: State<CategoriesState> = _categorieState

    private fun fetchCategories() {
        viewModelScope.launch {
            _categorieState.value = CategoriesState(loading = true)
            _categorieState.value = try {
                CategoriesState(list = repository.getCategories())
            } catch (e: Exception) {
                CategoriesState(error = e.localizedMessage)
            }
        }
    }
}

data class CategoriesState(
    val loading: Boolean = false,
    val list: List<Category> = emptyList(),
    val error: String? = null
)