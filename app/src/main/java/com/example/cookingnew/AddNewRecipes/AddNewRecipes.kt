package com.example.cookingnew.AddNewRecipes

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingnew.R
import com.example.cookingnew.splash.CircleTransform
import com.example.cookingnew.ui.my_recipes.recipe
import com.example.cookingnew.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_new_recipes.*

import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cookingnew.AddNewRecipes.SQLRecipe.RecipesRepository
import com.example.cookingnew.AddNewRecipes.camera.Picker
import com.example.cookingnew.AddNewRecipes.camera.Sources

class AddNewRecipes : AppCompatActivity() {

    private lateinit var ivFotoNewRecImage: ImageView

    lateinit var result1:Any
    var recipes: recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_recipes)
        ivFotoNewRecImage = findViewById(R.id.FotoNewRec)


        val fabCamera = findViewById<FloatingActionButton>(R.id.fab_pick_camera)
        val fabGallery = findViewById<FloatingActionButton>(R.id.fab_pick_gallery)
        fabCamera.setOnClickListener { v -> pickImageFromSource(Sources.CAMERA) }
        fabGallery.setOnClickListener {v ->  pickImageFromSource(Sources.GALLERY) }

            SpinnerAdapter()

        buttonSave.setOnClickListener { v -> saveRecipes() }
        val intent = intent
        if (intent != null && intent.hasExtra(Constants.INTENT_OBJECT)) {
            val recipes: recipe = intent.getParcelableExtra(Constants.INTENT_OBJECT)
            this.recipes = recipes
            LinkFun(recipes)

        }
    }

    private fun pickImageFromSource(source: Sources) {
        Picker.with(supportFragmentManager).requestImage(source, getString(R.string.label_pick_image))
            .subscribe({
                onImagePicked(it)
            }, {
                Toast.makeText(this@AddNewRecipes, java.lang.String.format("Error: %s", it), Toast.LENGTH_LONG).show()
            })
    }

    private fun onImagePicked(result: Any) {
        Toast.makeText(this, java.lang.String.format("Result: %s", result), Toast.LENGTH_LONG).show()
        if (result is Bitmap) {
            ivFotoNewRecImage.setImageBitmap(result)
        } else {
            result1 = result
            Glide.with(this)
                .load(result)
                .transition(DrawableTransitionOptions().crossFade())
                .into(ivFotoNewRecImage)
        }
    }

    private fun LinkFun(Recipes: recipe) {
        editNameRec.setText(Recipes.nameR)
        editTimeRec.setSelection(Recipes.timeCooking.toInt())
        editDescribeRec.setText(Recipes.describe)
        editInRec.setText(Recipes.ingredients)
        val textType = findViewById<TextView>(R.id.textType)
        textType.text = Recipes.type
        ivFotoNewRecImage = findViewById(R.id.FotoNewRec)
        Picasso.get()
            .load(Recipes.url)
            //.with(itemView.context)
            .placeholder(R.drawable.no_image)
            .transform(CircleTransform())
            .error(R.drawable.no_image)
            .into(ivFotoNewRecImage)
    }


    fun SpinnerAdapter() {
        val spinner: Spinner = findViewById(R.id.spinnerTypeRec)
        ArrayAdapter.createFromResource(
            this,
            R.array.Type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

     fun saveRecipes () {

         val name: String = editNameRec.text.toString()
         val ingredients: String = editInRec.text.toString()
         if (name.trim { it <= ' ' }.isEmpty() || ingredients.trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(this, "Введіть ім'я та інгрідієнт", Toast.LENGTH_SHORT).show()
             return

         }
         val id = if (recipes != null) recipes?.id else null
         val todo = recipe(id = id, nameR = editNameRec.text.toString(),
             type = spinnerTypeRec.selectedItem.toString(),
             timeCooking = editTimeRec.text.toString(),
             url = result1.toString(),
             describe = editDescribeRec.text.toString(),
             ingredients = editInRec.text.toString()
             )
         RecipesRepository(application).saveRecipes(todo)
         finish()
     }
}



