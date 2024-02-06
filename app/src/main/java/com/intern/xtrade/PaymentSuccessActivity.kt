package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.Fragments.PortfolioFragment

class PaymentSuccessActivity : AppCompatActivity() {
    lateinit var DoneButton :AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)
        DoneButton = findViewById(R.id.DoneButtonId)

        DoneButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}