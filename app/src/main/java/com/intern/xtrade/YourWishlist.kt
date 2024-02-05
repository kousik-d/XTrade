package com.intern.xtrade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class YourWishlist : AppCompatActivity() {

    lateinit var YourWishListBack : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_your_wishlist)
        YourWishListBack = findViewById(R.id.YourWishlistBackId)

        YourWishListBack.setOnClickListener {
            finish()
        }
    }
}