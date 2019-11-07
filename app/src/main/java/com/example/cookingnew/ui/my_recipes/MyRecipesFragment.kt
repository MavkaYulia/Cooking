package com.example.cookingnew.ui.my_recipes

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingnew.R
import kotlinx.android.synthetic.main.fragment_my_recipes.*
import org.jsoup.Jsoup
import java.io.IOException

class MyRecipesFragment : Fragment() {

    private lateinit var myRecipesViewModel: MyRecipesViewModel

    private val url1 = "https://znaj.ua/recepty"
    val ListRecipes1: MutableList<recipe> = mutableListOf()


    private lateinit var homeViewModel: MyRecipesViewModel
    private lateinit var adapter: AdapterRecyclerRecipes


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myRecipesViewModel =
            ViewModelProviders.of(this).get(MyRecipesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_recipes, container, false)
     ///   val textView: TextView = root.findViewById(R.id.text_home)
      ///  myRecipesViewModel.text.observe(this, Observer {
       //     textView.text = it
    //    })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        //RecipesList.layoutManager = LinearLayoutManager(context)
        //  RecipesList.adapter = AdapterRecyclerRecipes()
        adapter = AdapterRecyclerRecipes()
        val llm = LinearLayoutManager(this.context)
        RecipesList.layoutManager = llm
        RecipesList.adapter = adapter
        //AdapterRecyclerRecipes(this, viewModel.displayRecipes1)

        //adapter//(displayRecipes1 , this)


        GlobalScope.launch {
            getData()
        }
    }

    fun filter(text: String) {
        adapter.setFilterText(text)
    }


    private fun getData() {
        try {

            val document = Jsoup.connect(url1).get()

            val element = document.select("div[class=b_post b_post--image-sm]")
            for (i in 0 until element.size) {
                val nameR =
                    element.select("div[class=b_post--media]")
                        .select("h4")
                        .eq(i)
                        .text()

                val wocb =
                    element.select("div[class=b_post--description]")
                        .select("div")
                        .eq(i)
                        .text()

                val url =
                    //   document.baseUri()+
                    element.select("div[class=b_post b_post--image-sm]")
                        .select("img")
                        .eq(i)
                        .attr("src")

                val site =
                    element.select("div[class=b_post--date]")
                        .select("div")
                        .eq(i)
                        .text()

                ListRecipes1.add(recipe(nameR, wocb, url,site))
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter.setRecipes(ListRecipes1)
            }
        } catch (e: IOException) {
            //  e.printStackTrace()
        }
    }

}
