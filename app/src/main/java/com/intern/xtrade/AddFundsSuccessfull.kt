package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes

class AddFundsSuccessfull : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var closeButton : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_funds_successfull)
        closeButton = findViewById(R.id.close)
        sharedPreferences = getSharedPreferences("MONEY",Context.MODE_PRIVATE)
        val funds = intent.getIntExtra("FUNDTOADD",0)

        val totalFunds = sharedPreferences.getInt("AVAILABLEINR",0)
        sharedPreferences.edit().putInt("AVAILABLEINR",funds+totalFunds).apply()

        closeButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }

}