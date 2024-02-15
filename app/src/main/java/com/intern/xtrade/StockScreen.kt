package com.intern.xtrade

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
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
    private val buttons = mutableListOf<Button>()

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

        initButtons()

        StockBuyButton.setOnClickListener {
            val intent = Intent(this, Buy_activity::class.java)
            intent.putExtra("STOCKID",stockId)
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


    private fun initButtons() {
        val buttonIds = arrayOf(
            R.id.stock_button_1H,
            R.id.stock_button_24H,
            R.id.stock_button_1W,
            R.id.stock_button_1M,
            R.id.stock_button_6M,
            R.id.stock_button_1Y,
            R.id.stock_button_ALL,
        )
        for (id in buttonIds) {
            val button = findViewById<Button>(id)
            button.setOnClickListener { onButtonClick(button) }
            buttons.add(button)
        }
    }
    private fun onButtonClick(clickedButton: Button) {
        val drawable1: Drawable = this.getDrawable(R.drawable.stock_screen_button_clicked)!!
        val drawable2: Drawable = this.getDrawable(R.drawable.stock_screen_button)!!

        clickedButton.background = drawable1
        clickedButton.setTextColor(ContextCompat.getColor(this,R.color.card_blue))

        for (button in buttons) {
            if (button.id != clickedButton.id) {
                button.background = drawable2
                button.setTextColor(ContextCompat.getColor(this,R.color.darkBlack))
            }
        }
    }
}