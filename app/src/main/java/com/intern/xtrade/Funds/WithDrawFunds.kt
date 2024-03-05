package com.intern.xtrade.Funds

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
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
    lateinit var sharedPreferences2: SharedPreferences
    lateinit var BankSelection : AppCompatSpinner
    var withDrawAmount =0

    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("With_Draw_funds")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        BankSelection = findViewById(R.id.addFunds_dropDown)
        InitalizeSpinner()

        withDrawBack = findViewById(R.id.withdraw_back)
        withDrawFundsAmount = findViewById(R.id.WithDrawFunds_amount)
        withDrawButton = findViewById(R.id.withDrawButton)
        balance = findViewById(R.id.withDraw_balance)

        sharedPreferences2 = getSharedPreferences("USERATTR",Context.MODE_PRIVATE)

        var count = sharedPreferences2.getInt("WITHDRAWFUNDCOUNT",0)

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
            count= count+1
            Log.i("WITHDRAW FUND COUNT","$count")
            sharedPreferences2.edit().putInt("WITHDRAWFUNDCOUNT",count).apply()
            var withAmount = sharedPreferences2.getInt("WITHDRAWAMOUNTIN",0)
            withAmount += amountfinal
            sharedPreferences2.edit().putInt("WITHDRAWAMOUNTIN",withAmount).apply()

            val attrs2 = Attributes();
            attrs2.putAttribute("Number_of_Funds_withdrawed",count)
            ApxorSDK.setUserCustomInfo(attrs2)


            val attrs = Attributes();
            attrs.putAttribute("Funds_withdrawed",withDrawFundsAmount.text.toString().toFloat())
            ApxorSDK.setUserCustomInfo(attrs)

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