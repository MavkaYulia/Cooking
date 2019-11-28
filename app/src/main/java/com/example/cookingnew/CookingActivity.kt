package com.example.cookingnew

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.navigation.ui.*
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingnew.AddNewRecipes.AddNewRecipes
import com.example.cookingnew.ui.my_recipes.MyRecipesFragment


@Suppress("CAST_NEVER_SUCCEEDS")
class CookingActivity : AppCompatActivity() {


    lateinit var searchView: SearchView

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // val frag1 = fragmentManager.findFragmentById(R.id.nav_recipes)



        //AdapterRecyclerRecipes(

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_recipes,
                R.id.nav_favorites,
                R.id.nav_slideshow,
                R.id.nav_share,
                R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        searchView = menu.findItem(R.id.search_id)
            .actionView as SearchView
        // searchView.setSearchableInfo((getComponentName()))
        searchView.setMaxWidth(Integer.MAX_VALUE)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                myRecipesFragment?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                myRecipesFragment?.filter(query)
                return false
            }
        })
        return true
    }

    private val myRecipesFragment: MyRecipesFragment? by lazy {
        val navHostFragment = supportFragmentManager.fragments.first() as? NavHostFragment
        navHostFragment?.childFragmentManager?.fragments?.firstOrNull { it is MyRecipesFragment } as? MyRecipesFragment
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId


        return if (id == R.id.search_id) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        //return NavigationUI.navigateUp(navController, appBarConfiguration)|| super.onSupportNavigateUp()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

    fun AddNewPecipesFun(view: View){
        val NextIntent1 = Intent(this, AddNewRecipes::class.java)
        startActivity(NextIntent1)
    }

}
