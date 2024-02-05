package com.intern.xtrade

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.intern.xtrade.Fragments.PortfolioFragment


class FinalPayment : AppCompatActivity() {

    lateinit var moneyToshow : TextView
    lateinit var MoneyText: TextView
    lateinit var MoneyButton : Button
    lateinit var FinalPaymentHeading : TextView
    lateinit var FinalPaymentBackbtn : ImageView

    private var isFromWithDrawl = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_final_payment)
        moneyToshow = findViewById(R.id.MoneyToShowId)
        MoneyText = findViewById(R.id.moneyTextId)
        MoneyButton = findViewById(R.id.MoneyButtonId)
        FinalPaymentHeading = findViewById(R.id.FinalPaymentTextView)
        FinalPaymentBackbtn = findViewById(R.id.FinalPaymentBackId)

        FinalPaymentBackbtn.setOnClickListener{
            if(isFromWithDrawl==1){
                finish()
            }
        }

        val value = intent.getStringExtra("AVAILABLEINR")
        value?.apply {
            isFromWithDrawl = 1
            changeMoneyFromWithDrawl(value)
        }
        when(isFromWithDrawl){
            1 -> FinalPaymentHeading.text = "Withdraw Amount"
        }
    }

    private fun changeMoneyFromWithDrawl(money:String) {
        MoneyText.text = "AVAILABLE MONEY"
        moneyToshow.text = "₹ ${money}"
        MoneyButton.text = "WITHDRAW"
    }
}