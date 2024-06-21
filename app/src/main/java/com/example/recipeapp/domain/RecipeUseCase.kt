package com.example.recipeapp.domain

import Meal
import com.example.recipeapp.data.model.PreparedRecipe
import com.example.recipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {

    suspend fun fetchRecipeById(id: String): PreparedRecipe {
        val recipe = recipeRepository.fetchRecipeById(id)

        return prepareRecipe(recipe.meals.first())
    }

    suspend fun fetchListOfRecipesByCategory(categoryName: String): List<PreparedRecipe> =
        coroutineScope {
            val recipesResponse = recipeRepository.getRecipesByCategory(categoryName)
            val recipes = recipesResponse.recipesByCategories
            val preparedRecipes = mutableListOf<PreparedRecipe>()

            val deferredRecipes = recipes.map { recipe ->
                async {
                    try {
                        val fetchedRecipe = fetchRecipeById(recipe.idMeal)
                        preparedRecipes.add(fetchedRecipe)
                    } catch (e: Exception) {
                        println("Erro ao buscar a receita: ${e.message}")
                        null
                    }
                }
            }
            return@coroutineScope preparedRecipes
        }


    suspend fun getRecipesByCategory(category: String) =
        recipeRepository.getRecipesByCategory(category)


    private fun prepareRecipe(meal: Meal): PreparedRecipe {
        val ingredients = listOfNotNull(
            meal.strIngredient1?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient2?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient3?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient4?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient5?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient6?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient7?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient8?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient9?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient10?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient11?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient12?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient13?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient14?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient15?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient16?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient17?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient18?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient19?.takeIf { !it.isNullOrBlank() },
            meal.strIngredient20?.takeIf { !it.isNullOrBlank() }
        )

        val measures = listOfNotNull(
            meal.strMeasure1?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure2?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure3?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure4?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure5?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure6?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure7?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure8?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure9?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure10?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure11?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure12?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure13?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure14?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure15?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure16?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure17?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure18?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure19?.takeIf { !it.isNullOrBlank() },
            meal.strMeasure20?.takeIf { !it.isNullOrBlank() }
        )

        return PreparedRecipe(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strDrinkAlternate = meal.strDrinkAlternate,
            strCategory = meal.strCategory ?: "",
            strArea = meal.strArea ?: "",
            strInstructions = meal.strInstructions ?: "",
            strMealThumb = meal.strMealThumb ?: "",
            strTags = meal.strTags ?: "",
            strYoutube = meal.strYoutube ?: "",
            ingredients = ingredients,
            measures = measures,
            strSource = meal.strSource,
            strImageSource = meal.strImageSource,
            strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed,
            dateModified = meal.dateModified
        )
    }

}