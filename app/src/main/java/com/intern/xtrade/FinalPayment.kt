package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.Fragments.PortfolioFragment
import java.text.NumberFormat
import java.util.Locale


class FinalPayment : AppCompatActivity() {

    lateinit var moneyToshow : TextView
    lateinit var MoneyText: TextView
    lateinit var MoneyButton : Button
    lateinit var FinalPaymentHeading : TextView
    lateinit var FinalPaymentBackbtn : ImageView
    lateinit var sharedPreferences: SharedPreferences

    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("Final_payment")
    }

    var funds = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_final_payment)
        moneyToshow = findViewById(R.id.MoneyToShowId)
        MoneyText = findViewById(R.id.moneyTextId)
        MoneyButton = findViewById(R.id.MoneyButtonId)
        FinalPaymentHeading = findViewById(R.id.FinalPaymentTextView)
        FinalPaymentBackbtn = findViewById(R.id.FinalPaymentBackId)
        sharedPreferences = getSharedPreferences("USERATTR",Context.MODE_PRIVATE)

        var count = sharedPreferences.getInt("ADDFUNDCOUNT",0)



        intent.getStringExtra("FUNDAMOUNT").toString().let {
            funds = it.toInt()

        }
        val indiLocal = Locale("en", "in")

        moneyToshow.text = "${NumberFormat.getCurrencyInstance(indiLocal).format(funds)}"
        FinalPaymentBackbtn.setOnClickListener{
           finish()
        }
        MoneyButton.setOnClickListener {
            val intent : Intent = Intent(this, AddFundsSuccessfull::class.java)
            count = count+1
            sharedPreferences.edit().putInt("ADDFUNDCOUNT",count).apply()

            val attrs = Attributes();
            attrs.putAttribute("Funds_added",funds)
            ApxorSDK.setUserCustomInfo(attrs)

            val attrs2 = Attributes()
            attrs2.putAttribute("Amount",funds)
            ApxorSDK.logAppEvent("Funds_added",attrs2)
            intent.putExtra("FUNDTOADD",funds)
            startActivity(intent)
        }

    }
}