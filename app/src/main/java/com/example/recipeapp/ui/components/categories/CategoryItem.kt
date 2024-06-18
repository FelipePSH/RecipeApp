package com.example.recipeapp.ui.components.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.data.model.Category

@Composable
fun CategoryItem(category: Category) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = "image of a meal of category ${category.strCategory}",
            modifier = Modifier.height(56.dp).width(56.dp)
        )
        Text(
            text = category.strCategory,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 1.dp),
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        Category(
            "1",
            "beef",
            strCategoryThumb = "https://www.themealdb.com/images/category/beef.png",
            strCategoryDescription = "beef"))
}



