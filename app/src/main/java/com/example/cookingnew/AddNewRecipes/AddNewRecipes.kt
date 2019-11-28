package com.example.cookingnew.AddNewRecipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cookingnew.R
import com.example.cookingnew.splash.CircleTransform
import com.example.cookingnew.ui.my_recipes.recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_new_recipes.*


class AddNewRecipes : AppCompatActivity() {

    val EXTRA_ID = "com.example.cookingnew.AddNewRecipes.EXTRA_ID"
    val EXTRA_NAMER = "com.example.cookingnew.AddNewRecipes.EXTRA_NAMER"
    val EXTRA_TYPE = "com.example.cookingnew.EXTRA_TYPE"
    val EXTRA_TIMECOOKING = "com.example.cookingnew.EXTRA_TIMECOOKING"
    val EXTRA_URL = "com.example.cookingnew.EXTRA_URL"
    val EXTRA_DESCRIBE = "com.example.cookingnew.EXTRA_DESCRIBE"
    val EXTRA_INGREDIENTS = "com.example.cookingnew.EXTRA_INGREDIENTS"

    var recipes: recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_recipes)
        SpinnerAdapter()

        val intent = intent
        if (intent != null && intent.hasExtra(EXTRA_NAMER)) {
           // val recipes: recipe = intent.getParcelableExtra(EXTRA_NAMER)
            this.recipes = recipes
           // LinkFun(recipes)
            val button = findViewById<Button>(R.id.buttonSave)
            button.setOnClickListener {
                val replyIntent = Intent()
                if (TextUtils.isEmpty(editNameRec.text)) {
                    setResult(Activity.RESULT_CANCELED, replyIntent)
                } else {
                    val word = editNameRec.text.toString()
                    replyIntent.putExtra(EXTRA_NAMER, word)
                    setResult(Activity.RESULT_OK, replyIntent)
                }
                finish()
            }

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

     fun saveRecipes (Recipe : recipe) {

         val name : String = editNameRec.text.toString()
         val type : String = spinnerTypeRec.selectedItemId.toString()
         val describe : String = editDescribeRec.text.toString()
         val ingredients : String = editInRec.text.toString()
       //  val url : String = FotoNewRec.text.toString()
        // val time: Int = editTimeRec.getValue()

         if (name.trim { it <= ' ' }.isEmpty() || ingredients.trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(this, "Please name a title and ingredients", Toast.LENGTH_SHORT).show()
             return
         }

         val data = Intent()
         data.putExtra(EXTRA_NAMER, name)
         data.putExtra(
             EXTRA_TYPE,
             type
         )
       //  data.putExtra(
         //    EXTRA_TIMECOOKING,
         //    time
        // )
       //  data.putExtra(
        //     EXTRA_URL,
         //    url
       // )

         data.putExtra(
             EXTRA_INGREDIENTS,
             ingredients
         )
         data.putExtra(
             EXTRA_DESCRIBE,
             describe)

         val id = intent.getIntExtra(
             EXTRA_ID,
             -1
         )
         if (id != -1) {
             data.putExtra(EXTRA_ID, id)
         }

         setResult(Activity.RESULT_OK, data)
         finish()
        }


    }



