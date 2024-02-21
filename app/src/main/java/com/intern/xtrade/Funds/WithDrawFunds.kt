package com.intern.xtrade.Funds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    lateinit var balance : TextView
    lateinit var warning : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        withDrawBack = findViewById(R.id.withdraw_back)
        withDrawFundsAmount = findViewById(R.id.WithDrawFunds_amount)
        withDrawButton = findViewById(R.id.withDrawButton)
        balance = findViewById(R.id.withDraw_balance)
        warning = findViewById(R.id.withDraw_warning)



        withDrawButton.setOnClickListener {
            finish()
        }

        warning.visibility = TextView.GONE


        withDrawFundsAmount.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString()!="") {
                    val amount = s.toString().toInt()
                    if (amount > balance.text.toString().toInt())
                        warning.visibility = TextView.VISIBLE
                    else
                        warning.visibility = TextView.GONE
                }

            }

        })


        withDrawBack.setOnClickListener {
            finish()
        }


    }
}