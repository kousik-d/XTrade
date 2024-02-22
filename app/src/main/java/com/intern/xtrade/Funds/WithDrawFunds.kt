package com.intern.xtrade.Funds

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import com.intern.xtrade.R
import java.text.NumberFormat
import java.util.Locale

class WithDrawFunds : AppCompatActivity() {
    lateinit var withDrawBack : ImageView
    lateinit var withDrawFundsAmount: EditText
    lateinit var withDrawFundsWarning : TextView
    lateinit var withDrawButton : AppCompatButton
    lateinit var balance : TextView
    lateinit var warning : TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var BankSelection : AppCompatSpinner
    var withDrawAmount =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        BankSelection = findViewById(R.id.addFunds_dropDown)
        InitalizeSpinner()

        withDrawBack = findViewById(R.id.withdraw_back)
        withDrawFundsAmount = findViewById(R.id.WithDrawFunds_amount)
        withDrawButton = findViewById(R.id.withDrawButton)
        balance = findViewById(R.id.withDraw_balance)

        sharedPreferences = getSharedPreferences("MONEY", Context.MODE_PRIVATE)
        val availableMoney = sharedPreferences.getInt("AVAILABLEINR",0)
        val indiLocal = Locale("en", "in")
        balance.text = NumberFormat.getCurrencyInstance(indiLocal).format(availableMoney)
        warning = findViewById(R.id.withDraw_warning)



        warning.visibility = TextView.GONE


        withDrawButton.isClickable = false
        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
        var amountfinal = 0

        withDrawFundsAmount.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                if(s!=null && s.toString()!="") {
                    val amount = s.toString().toInt()
                    if (amount > availableMoney) {
                        warning.visibility = TextView.VISIBLE
                        withDrawButton.isClickable = false
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                    }
                    else if ( amount==0) {
                        warning.visibility = TextView.GONE
                        withDrawButton.isClickable = false
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                    }
                    else{
                        amountfinal = amount
                        warning.visibility = TextView.GONE
                        withDrawButton.isClickable = true
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.card_blue))
                    }
                }
                else
                {
                    withDrawButton.isClickable = false
                    withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                }

            }

        })

        withDrawButton.setOnClickListener {
                sharedPreferences.edit().putInt("AVAILABLEINR",availableMoney-amountfinal).apply()
                Toast.makeText(this,"Transaction will be processed shortly !",Toast.LENGTH_SHORT).show()
        }


        withDrawBack.setOnClickListener {
            finish()
        }


    }
    fun InitalizeSpinner(){
        var BankOptions = arrayOf(
            "ICICI",
            "IOB",
            "AXIS",
            "HDFC",
        )
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, BankOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        BankSelection.adapter = adapter
    }
}