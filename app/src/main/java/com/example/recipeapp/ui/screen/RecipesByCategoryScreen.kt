package com.example.recipeapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.model.PreparedRecipe
import com.example.recipeapp.ui.components.recipe.RecipeCardItem
import com.example.recipeapp.ui.viewmodel.CategoriesViewModel
import com.example.recipeapp.ui.viewmodel.RecipesViewModel

@Composable
fun RecipesByCategoryScreen(
    category: Category,
    recipeViewModel: RecipesViewModel = hiltViewModel()
) {


    val viewStateRecipe by recipeViewModel.recipeState

    LaunchedEffect(key1 = category) {
        recipeViewModel.fetchListOfRecipesByCategory(category.strCategory)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = category.strCategory,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        when {
            viewStateRecipe.isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            viewStateRecipe.error != null -> {
                Text(text = "Error occurred ${viewStateRecipe.error}")
            }

            else -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    MealsByCategoryColumn(meals = viewStateRecipe.recipe)
                }
            }
        }
    }
}

@Composable
fun MealsByCategoryColumn(meals: List<PreparedRecipe>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(meals) { meal ->
            RecipeCardItem(recipe = meal)
        }
    }
}
