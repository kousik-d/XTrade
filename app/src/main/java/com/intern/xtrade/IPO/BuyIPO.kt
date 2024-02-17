package com.intern.xtrade.IPO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.intern.xtrade.R

class BuyIPO : AppCompatActivity() {

    lateinit var IPOBackImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ipo)
        IPOBackImage = findViewById(R.id.IPO_back)

        IPOBackImage.setOnClickListener {
            finish()
        }
    }
}