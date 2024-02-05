package com.intern.xtrade.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.intern.xtrade.R

class DepositINRActivity : AppCompatActivity() {

    lateinit var PaymentAmount : EditText
    lateinit var backBtn :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_final_payment_edt)

        PaymentAmount = findViewById(R.id.payment_amount)
        backBtn = findViewById(R.id.DepositMoneyBackId)

        backBtn.setOnClickListener {
            finish()
        }

    }
}