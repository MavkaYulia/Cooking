package com.example.cookingnew.AddNewRecipes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingnew.R
import com.example.cookingnew.splash.CircleTransform
import com.example.cookingnew.ui.my_recipes.recipe
import com.example.cookingnew.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_new_recipes.*


class AddNewRecipes : AppCompatActivity() {

    var recipes: recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_recipes)
        SpinnerAdapter()

        val intent = intent
        if (intent != null && intent.hasExtra(Constants.INTENT_OBJECT)) {
            val recipes: recipe = intent.getParcelableExtra(Constants.INTENT_OBJECT)
            this.recipes = recipes
            LinkFun(recipes)
        }
    }

    private fun LinkFun(Recipes: recipe) {
        editNameRec.setText(Recipes.nameR)
        editTimeRec.setSelection(Recipes.timeCooking.toInt())
        editDescribeRec.setText(Recipes.describe)
        editInRec.setText(Recipes.ingredients)
        val textType = findViewById<TextView>(R.id.textType)
        textType.text = Recipes.type
        val myImageView = findViewById<ImageView>(R.id.FotoNewRec)
        Picasso.get()
            .load(Recipes.url)
            //.with(itemView.context)
            .placeholder(R.drawable.food2)
            .transform(CircleTransform())
            .error(R.drawable.no_image)
            .into(myImageView)
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

    @SuppressLint("NewApi")
    private fun saveRecipes(Recipes: recipe) {
        if (editNameRec != null) {
            val textType = findViewById<Spinner>(R.id.spinnerTypeRec)

            val id = if (recipes != null) recipes?.id else null
            val Recipes1 = recipe(
                id = id, nameR = editNameRec.text.toString(),
                type = textType.selectedItemId.toString(),
                timeCooking = editTimeRec.text.toString(),
                describe = editDescribeRec.text.toString(),
                ingredients = editInRec.text.toString(),
                url = FotoNewRec.imageTintBlendMode.toString()
            )
            val intent = Intent()
            intent.putExtra(Constants.INTENT_OBJECT, Recipes1)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}



/**
     * Validation of EditText
     * */

