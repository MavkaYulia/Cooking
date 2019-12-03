package com.example.cookingnew.AddNewRecipes.SQLRecipe

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cookingnew.ui.my_recipes.recipe

@Dao
interface RecipeDao {
    @Insert
    suspend fun saveRecipes(Recipe: recipe)

    @Update
    suspend fun updateRecipes(Recipe: recipe)

    @Delete
    suspend fun deleteRecipes(Recipe: recipe)


    @Query("SELECT * FROM Recipes ORDER BY id DESC")
    fun getRecipesList(): LiveData<List<recipe>>

}