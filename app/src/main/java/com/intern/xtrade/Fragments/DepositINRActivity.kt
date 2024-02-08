package com.intern.xtrade.Fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.PaymentSuccessActivity
import com.intern.xtrade.R

class DepositINRActivity : AppCompatActivity() {

    lateinit var PaymentAmount : EditText
    lateinit var backBtn :ImageView
    lateinit var DepositButton : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_final_payment_edt)

        PaymentAmount = findViewById(R.id.payment_amount)
        backBtn = findViewById(R.id.DepositMoneyBackId)
        DepositButton = findViewById(R.id.MoneyButtonId)
        val depositedAmount = PaymentAmount.text
        DepositButton.setOnClickListener {
            if(PaymentAmount.text.isNotEmpty()) {
                val intent = Intent(this, PaymentSuccessActivity::class.java)
                intent.putExtra("DEPOSITEDMONEY", depositedAmount.toString().toInt())
                startActivity(intent)
            }else{
                Toast.makeText(this,"Deposit should not be Empty",Toast.LENGTH_SHORT).show()
            }
        }
        backBtn.setOnClickListener {
            finish()
        }

    }
}