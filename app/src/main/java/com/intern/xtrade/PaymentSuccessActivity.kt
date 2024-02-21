package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.Fragments.PortfolioFragment
import com.intern.xtrade.wishList.WishlistManager

class PaymentSuccessActivity : AppCompatActivity() {
    lateinit var DoneButton :AppCompatButton
    lateinit var PaymentSuccessMessage : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        DoneButton = findViewById(R.id.DoneButtonId)
        PaymentSuccessMessage = findViewById(R.id.PaymentSuccessMessage)
        val depositedMoney = intent.getIntExtra("DEPOSITEDMONEY",-100)
        val stockId = intent.getIntExtra("STOCKID",0)
        val sellStockId = intent.getIntExtra("SELLSTOCKID",-1)

        if(sellStockId!=-1){
            WishlistManager.removeFromYourStocks(this,sellStockId)
        }

        if(depositedMoney != -100){
            PaymentSuccessMessage.text = "Deposited Successfully"
        }
        DoneButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("DEPOSITEDMONEY",depositedMoney)
            startActivity(intent)
        }
    }
}