package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SearchBarActivity : AppCompatActivity() {
    lateinit var cancelText : TextView
    lateinit var yourWishList : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)

        cancelText = findViewById(R.id.CancelTextId)
        yourWishList = findViewById(R.id.wishListId)

        cancelText.setOnClickListener {
            finish()
        }

        yourWishList.setOnClickListener {
            val intent = Intent(this,YourWishlist::class.java)
            startActivity(intent)
        }
    }
}