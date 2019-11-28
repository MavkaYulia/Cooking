package com.example.cookingnew.AddNewRecipes.SQLRecipe

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cookingnew.ui.my_recipes.recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecipesRepository(application: Application) {

    private val recipeDao: RecipeDao
    private val allRecipes: LiveData<List<recipe>>

    init {
        val database = RecipesDataBase.getInstance(application.applicationContext)
        recipeDao = database!!.recipeDao()
        allRecipes = recipeDao.getRecipesList()
    }

    fun saveRecipes (Recipe : recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDao.saveRecipes(Recipe)
        }
    }
    fun updateRecipes (Recipe : recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDao.updateRecipes(Recipe)
        }
    }

    fun deleteRecipes (Recipe : recipe) { runBlocking {
   this.launch(Dispatchers.IO){
       recipeDao.deleteRecipes(Recipe)
             }
        }
    }
    fun getRecipesList() : LiveData<List<recipe>> {
        return allRecipes
    }

}

