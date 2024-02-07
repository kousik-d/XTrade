package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.Fragments.PortfolioFragment
import com.intern.xtrade.wishList.WishlistManager

class PaymentSuccessActivity : AppCompatActivity() {
    lateinit var DoneButton :AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        DoneButton = findViewById(R.id.DoneButtonId)
        val depositedMoney = intent.getIntExtra("DEPOSITEDMONEY",0)
        val stockId = intent.getIntExtra("STOCKID",0)

        WishlistManager.addToYourStocks(this,stockId)

        DoneButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("DEPOSITEDMONEY",depositedMoney)
            startActivity(intent)
        }
    }
}