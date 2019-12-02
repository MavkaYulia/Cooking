package com.example.cookingnew.ui.my_recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.cookingnew.R
import com.example.cookingnew.splash.CircleTransform


class AdapterRecyclerRecipes : RecyclerView.Adapter<AdapterRecyclerRecipes.ViewHolder>() {

    //interface RecipesEvents {
    //    fun onDeleteClicked(Recipes1: recipe )
    //    fun onViewClicked(Recipes1: recipe )
   // }

    private var recipesList =  emptyList<recipe>()
    private var filterText: String = ""
    private var filteredList: List<recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.items_recipes, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = filteredList.size

//-------------------------->
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }


    fun setRecipes(list: List<recipe>) {
        this.recipesList = list
        filterAndNotify()
   }

    fun setFilterText(text: String = "") {
        filterText = text
        filterAndNotify()
    }

    private fun filterAndNotify() {
        filteredList = recipesList.filter {
            it.getName().contains(filterText, true)
        }
        notifyDataSetChanged()
    }

    fun setAllRecipesItems ( recipeItems : List<recipe>){
        this.recipesList = recipeItems
        this.filteredList = recipeItems
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textNameRec = itemView.findViewById<TextView>(R.id.textName)
        val myImageView = itemView.findViewById<ImageView>(R.id.imageRec)
        val textType = itemView.findViewById<TextView>(R.id.textType)
        val textDescribe = itemView.findViewById<TextView>(R.id.textDescribe)
        val textTime = itemView.findViewById<TextView>(R.id.textTime)
        val textIngredients = itemView.findViewById<TextView>(R.id.textIngredients)



        fun bind(Recipes1: recipe ) {

            textNameRec.text = Recipes1.nameR
            textType.text = Recipes1.type
            textDescribe.text = Recipes1.describe
            textTime.text = Recipes1.timeCooking.toString()
            textIngredients.text = Recipes1.ingredients

            Picasso.get()
                .load(Recipes1.url)
                //.with(itemView.context)
                .placeholder(R.drawable.food2)
                .transform(CircleTransform())
                .error(R.drawable.no_image)
                .into(myImageView)

        }

    }
}