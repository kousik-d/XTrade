package com.intern.xtrade.Orders

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R
import com.intern.xtrade.wishList.WishlistManager

class OrderConfirmed : AppCompatActivity() {
    lateinit var goToOrderbtn : AppCompatButton
    lateinit var OrderConfirmBack :ImageView
    lateinit var DoneButton :AppCompatButton
    lateinit var sharedPrefereneForUserAttributes : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmed)
        sharedPrefereneForUserAttributes = getSharedPreferences("USERATTR", Context.MODE_PRIVATE)
        var c = sharedPrefereneForUserAttributes.getInt("STOCKBUYCOUNT",3)
        c+=1
        sharedPrefereneForUserAttributes.edit().putInt("STOCKBUYCOUNT",c).apply()

        goToOrderbtn = findViewById(R.id.orderPlaced_OrdersBtn)
        OrderConfirmBack = findViewById(R.id.orderPlaced_back)
        val stockId = intent.getIntExtra("STOCKID",0)

        WishlistManager.addToYourStocks(this,stockId)

        goToOrderbtn.setOnClickListener {
            val intent = Intent(this,YourOrders::class.java)
            intent.putExtra("Source","order_bottom_bar")
            startActivity(intent)
        }

//        OrderConfirmBack.setOnClickListener {
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onBackPressed() {
        if(false) {
            super.onBackPressed()
        }
    }
}