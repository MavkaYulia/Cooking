package com.example.cookingnew.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.cookingnew.CookingActivity
import com.example.cookingnew.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)
        val intent = Intent(this, CookingActivity::class.java)
        startActivity(intent)
        finish()
    }
}
