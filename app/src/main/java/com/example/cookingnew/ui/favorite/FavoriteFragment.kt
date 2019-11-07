package com.example.cookingnew.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cookingnew.R

class FavoriteFragment : Fragment() {

    private lateinit var galleryViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        // val textView: TextView = root.findViewById(R.id.text_favorites)
       // galleryViewModel.text.observe(this, Observer {
            //     textView.text = it
       // })
        return root

    }

}