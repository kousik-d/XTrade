package com.intern.xtrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class StockScreen : AppCompatActivity() {

    lateinit var stockLogoImage : ImageView
    lateinit var stockName : TextView
    lateinit var stockValue : TextView
    lateinit var stockScreenPercentage : TextView
    lateinit var stockScreenBuyButton : Button
    lateinit var stockScreenSellButton : Button
    lateinit var stockScreenBack : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_screen)

        val companyName = intent.getStringExtra("STOCKNAME")
        val companyLogo= intent.getStringExtra("COMPANYLOGO")?.toInt()
        val stockPrice = intent.getStringExtra("STOCKPRICE")?.toDouble()
        val stockPercentage = intent.getStringExtra("STOCKPERCENTAGE")?.toDouble()
        val stockBoolean = intent.getBooleanExtra("GRAPHBOOLEAN",false)

        stockName = findViewById(R.id.stock_name)
        stockLogoImage = findViewById(R.id.stock_logo)
        stockValue = findViewById(R.id.stock_screen_value)
        stockScreenPercentage = findViewById(R.id.stock_screen_percentage)
        stockScreenBuyButton = findViewById(R.id.Stock_Screen_BuyButton)
        stockScreenSellButton = findViewById(R.id.Stock_Screen_SellButton)
        stockScreenBack = findViewById(R.id.BackButtonImage)

        stockScreenBack.setOnClickListener {
            finish()
        }

        if(stockBoolean){
            stockScreenPercentage.text = "+ ${stockPercentage}"
            stockScreenPercentage.setTextColor(getColor(R.color.green))
        }else{
            stockScreenPercentage.text = "- ${stockPercentage}"
            stockScreenPercentage.setTextColor(getColor(R.color.red))
        }
        stockValue.text = "₹ ${stockPrice}"

        stockName.text = companyName
        if(companyLogo!=null)
        stockLogoImage.setImageResource(companyLogo)


=======

class StockScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_screen)
>>>>>>> eaa839231dd42db111e140c41d10efed3effa036
    }
}