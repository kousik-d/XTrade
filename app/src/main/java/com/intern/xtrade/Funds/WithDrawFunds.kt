package com.intern.xtrade.Funds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.R

class WithDrawFunds : AppCompatActivity() {
    lateinit var withDrawBack : ImageView
    lateinit var withDrawFundsAmount: EditText
    lateinit var withDrawFundsWarning : TextView
    lateinit var withDrawButton : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        withDrawBack = findViewById(R.id.withdraw_back)
        withDrawFundsAmount = findViewById(R.id.WithDrawFunds_amount)
        withDrawFundsWarning = findViewById(R.id.addFunds_warning)
        withDrawButton = findViewById(R.id.withDrawButton)

        withDrawButton.setOnClickListener {
            finish()
        }


        withDrawBack.setOnClickListener {
            finish()
        }


    }
}