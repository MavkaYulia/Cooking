package com.example.cookingnew.ui.my_recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingnew.AddNewRecipes.SQLRecipe.RecipesRepository

class MyRecipesViewModel (application: Application): AndroidViewModel(application) {
        private val repository : RecipesRepository = RecipesRepository(application)
        private val allRecipeList : LiveData<List<recipe>> = repository.getRecipesList()

    fun saveRecipes(Recipe : recipe) {
        repository.saveRecipes(Recipe = Recipe)
    }

    fun updateRecipes(Recipe : recipe) {
        repository.updateRecipes(Recipe = Recipe)
    }

    fun deleteRecipes(Recipe : recipe) {
        repository.deleteRecipes(Recipe = Recipe)
    }

    fun getRecipesList() : LiveData<List<recipe>> {
        return allRecipeList
    }
}