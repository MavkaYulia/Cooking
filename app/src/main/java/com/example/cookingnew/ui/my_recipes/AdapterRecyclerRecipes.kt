package com.example.cookingnew.ui.my_recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.cookingnew.R
import com.example.cookingnew.splash.CircleTransform


class AdapterRecyclerRecipes  : RecyclerView.Adapter<AdapterRecyclerRecipes.ViewHolder>() {


    private var recipes: List<recipe> = listOf()
    private var filterText: String = ""
    private var filteredList: List<recipe> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.items_recipes, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = filteredList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }


    fun setRecipes(list: MutableList<recipe>) {
        recipes = list
        filterAndNotify()
    }

    fun setFilterText(text: String = "") {
        filterText = text
        filterAndNotify()
    }

    private fun filterAndNotify() {
        filteredList = recipes.filter {
            it.getName().contains(filterText, true)
        }
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textNameRec = itemView.findViewById<TextView>(R.id.textName)
        val textWocb = itemView.findViewById<TextView>(R.id.textWocb)
        val textSite = itemView.findViewById<TextView>(R.id.textSite)
        val myImageView = itemView.findViewById<ImageView>(R.id.imageRec)

        fun bind(Recipes1: recipe) {

            textNameRec.text = Recipes1.nameR
            textWocb.text = Recipes1.wocb
            textSite.text = Recipes1.site

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