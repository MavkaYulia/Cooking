package com.example.cookingnew.AddNewRecipes.SQLRecipe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cookingnew.ui.my_recipes.recipe


@Database ( entities  = [recipe::class],version = 1 , exportSchema = false )

abstract class RecipesDataBase : RoomDatabase()
{
    abstract fun recipeDao() : RecipeDao

    companion object{
        private var INSTANCE : RecipesDataBase? = null

        fun getInstance(context: Context) : RecipesDataBase? {
            if (INSTANCE==null) {
            synchronized(RecipesDataBase::class){
                INSTANCE = Room.databaseBuilder(context
                    ,RecipesDataBase::class.java
                    , "recipes.db")
                    .build() }
            }
            return INSTANCE
        }

    }
}