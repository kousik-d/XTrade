package com.intern.xtrade

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class YourStocks : AppCompatActivity() {

    lateinit var YourStockBackBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_your_stocks)

        YourStockBackBtn = findViewById(R.id.YourStocksBackBtn)

        YourStockBackBtn.setOnClickListener { finish() }
    }
}