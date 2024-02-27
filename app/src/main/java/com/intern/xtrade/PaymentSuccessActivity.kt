package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    lateinit var sharedPrefereneForUserAttributes : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        DoneButton = findViewById(R.id.CloseCompatButton)
        sharedPrefereneForUserAttributes = getSharedPreferences("USERATTR", Context.MODE_PRIVATE)
        var c = sharedPrefereneForUserAttributes.getInt("STOCKBUYCOUNT",3)
        c-=1
        sharedPrefereneForUserAttributes.edit().putInt("STOCKBUYCOUNT",c).apply()
        DoneButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}