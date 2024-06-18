package com.example.recipeapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.components.categories.CategoryItem
import com.example.recipeapp.ui.viewmodel.CategoriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
    ){

    val viewState by categoriesViewModel.categoriesState


    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewState.isLoading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "Error occurred ${viewState.error}")
            }
            else -> {
                Column(modifier = modifier.fillMaxSize()) {

                    CategoriesList(categories = viewState.categories)
                }

            }

        }
    }
}

@Composable
fun CategoriesList(categories: List<Category>){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
      items(categories){ category ->
          CategoryItem(category = category)
      }
    }
}