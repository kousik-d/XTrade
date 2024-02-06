package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.intern.xtrade.RegularAndAMO.Buy_activity
import com.intern.xtrade.wishList.WishlistManager


class StockScreen : AppCompatActivity() {

    lateinit var stockLogoImage : ImageView
    lateinit var stockName : TextView
    lateinit var stockValue : TextView
    lateinit var stockScreenPercentage : TextView
    lateinit var stockScreenBuyButton : Button
    lateinit var stockScreenSellButton : Button
    lateinit var stockScreenBack : ImageView
    lateinit var AddToWishList : CheckBox
    lateinit var StockBuyButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_screen)

        val companyName = intent.getStringExtra("STOCKNAME")
        val companyLogo= intent.getIntExtra("COMPANYLOGO",R.drawable.apxor_x_logo)
        val stockPrice = intent.getDoubleExtra("STOCKPRICE",0.00)
        val stockPercentage = intent.getDoubleExtra("STOCKPERCENTAGE",0.00)
        val stockBoolean = intent.getBooleanExtra("GRAPHBOOLEAN",false)
        val stockId = intent.getIntExtra("STOCKID",0)



        stockName = findViewById(R.id.stock_name)
        stockLogoImage = findViewById(R.id.stock_logo)
        stockValue = findViewById(R.id.stock_screen_value)
        stockScreenPercentage = findViewById(R.id.stock_screen_percentage)
        stockScreenBuyButton = findViewById(R.id.Stock_Screen_BuyButton)
        stockScreenSellButton = findViewById(R.id.Stock_Screen_SellButton)
        stockScreenBack = findViewById(R.id.BackButtonImage)
        AddToWishList = findViewById(R.id.AddToWishListCheckBox)
        StockBuyButton = findViewById(R.id.Stock_Screen_BuyButton)

        StockBuyButton.setOnClickListener {
            val intent = Intent(this, Buy_activity::class.java)
            startActivity(intent)
        }
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
        val formatedval = String.format("%.2f",stockPrice)
        stockValue.text = "₹ ${formatedval}"

        stockName.text = companyName
        if(companyLogo!=null) {
            stockLogoImage.setImageResource(companyLogo)
        }

        val wishlist = WishlistManager.getWishlist(this)
        if(wishlist.contains(stockId)){
            AddToWishList.isChecked = true
        }
        val wishlistManager = WishlistManager.getWishlist(this)

        AddToWishList.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                companyName?.let {
                    WishlistManager.addToWishlist(this,stockId)
                }
                //val wishlist = WishlistManager.getWishlist(this)
                Toast.makeText(this,"Added to wishlist",Toast.LENGTH_SHORT).show()
            }else{
                WishlistManager.removeFromWishlist(this,stockId)
                Toast.makeText(this,"Removed from wishlist",Toast.LENGTH_SHORT).show()

            }
        }
    }
}