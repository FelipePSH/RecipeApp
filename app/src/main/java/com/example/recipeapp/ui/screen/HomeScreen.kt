package com.example.recipeapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.ui.components.categories.CategoryItem
import com.example.recipeapp.ui.viewmodel.CategoriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(categoriesViewModel: CategoriesViewModel = hiltViewModel()) {
    val viewState by categoriesViewModel.categoriesState

    var text by remember {
        mutableStateOf("")
    }

    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            Modifier
                .fillMaxWidth()
                .semantics { var isTraversalGroup = true }) {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .semantics { traversalIndex = 0f },
                inputField = {
                    SearchBarDefaults.InputField(
                        query = text,
                        onQueryChange = { text = it },
                        onSearch = { expanded = false },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text("Example: Pizza") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    repeat(4) { idx ->
                        val resultText = "Suggestion $idx"
                        ListItem(
                            headlineContent = { Text(resultText) },
                            supportingContent = { Text("Additional info") },
                            leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier =
                            Modifier
                                .clickable {
                                    text = resultText
                                    expanded = false
                                }
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Text(
            text = "Ready to cook?",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(all = 16.dp)
        )

        CategoryRow(categories = viewState.categories)
    }
}

@Composable
fun CategoryRow(categories: List<Category>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize()
    ) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyRow(
        ) {
            items(categories) { category ->
                CategoryItem(category = category)
            }
        }
    }

}