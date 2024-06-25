package com.example.recipeapp.ui.components.recipe

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.data.model.PreparedRecipe

@Composable
fun RecipeCardItem(
    modifier: Modifier = Modifier,
    recipe: PreparedRecipe
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 0.dp),
        shape = MaterialTheme.shapes.extraSmall,
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(0.5.dp, color = Color.LightGray)
    ) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(all = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {

                recipe.strMeal?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }

                recipe.strArea?.let {
                    Text(
                        text =  it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }


                Text(
                    text = "Ingredients: ${recipe.ingredients?.size}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = modifier.padding(top = 6.dp)
                )

                Row(modifier = modifier.padding(top = 6.dp)) {
                    Text(
                        text = "Tags:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    recipe.strTags?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
                

            }
            Column {
                Image(
                    painter = rememberAsyncImagePainter(model = recipe.strMealThumb),
                    contentDescription = "image of: ${recipe.strMeal}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(140.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun PreviewRecipeCardItem() {
    val exampleRecipe = PreparedRecipe(
        idMeal = "52772",
        strMeal = "Spicy Arrabiata Penne",
        strDrinkAlternate = null,
        strCategory = "Vegetarian",
        strArea = "Italian",
        strInstructions = "Bring a large pot of water to a boil. Add salt and pasta, cook until al dente. In a large skillet, heat oil. Add garlic and chili flakes, cook until fragrant. Add tomatoes and simmer. Add cooked pasta to sauce and toss. Serve hot.",
        strMealThumb = "https://www.themealdb.com/images/media/meals/kos9av1699014767.jpg",
        strTags = "Pasta, Spicy",
        strYoutube = "https://www.youtube.com/watch?v=1IszT_guI08",
        ingredients = listOf(
            "Penne Rigate",
            "Olive Oil",
            "Garlic",
            "Chili Flakes",
            "Cherry Tomatoes",
            "Basil"
        ),
        measures = listOf("200g", "2 tbsp", "2 cloves", "1 tsp", "400g", "A handful"),
        strSource = "https://www.bbcgoodfood.com/recipes/6056360/spicy-arrabiata-penne",
        strImageSource = "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
        strCreativeCommonsConfirmed = "Yes",
        dateModified = "2022-10-12"
    )
    RecipeCardItem(recipe = exampleRecipe)
}