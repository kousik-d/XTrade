package com.intern.xtrade.Funds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContentProviderCompat.requireContext
import com.intern.xtrade.BackGroundChange.ButtonBackgroundChange
import com.intern.xtrade.FinalPayment
import com.intern.xtrade.Fragments.DepositINRActivity
import com.intern.xtrade.R

class AddFundsActivity : AppCompatActivity() {
    lateinit var FundAmount: EditText
    lateinit var BankSelection : AppCompatSpinner
    lateinit var FundsAddFiveHund : AppCompatButton
    lateinit var FundsAddThousand : AppCompatButton
    lateinit var FundsAddFiveThousand : AppCompatButton
    lateinit var FundsAddTenThousand : AppCompatButton
    lateinit var FundsBackButton : ImageView
    lateinit var FundsBalance : TextView
    lateinit var FundsWarning : TextView
    lateinit var xPayBtn : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_funds)
        BankSelection = findViewById(R.id.addFunds_dropDown)
        InitalizeSpinner()

        FundAmount = findViewById(R.id.addFunds_amount)
        FundsAddFiveHund = findViewById(R.id.addFunds_500)
        FundsAddThousand = findViewById(R.id.addFunds_1000)
        FundsAddFiveThousand = findViewById(R.id.addFunds_5000)
        FundsAddTenThousand = findViewById(R.id.addFunds_10000)
        FundsBackButton = findViewById(R.id.addFunds_back)
        FundsBalance = findViewById(R.id.addFunds_balance)
        FundsWarning = findViewById(R.id.addFunds_warning)
        xPayBtn = findViewById(R.id.xPay)

        FundsBackButton.setOnClickListener {
            finish()
        }

        var FundAmountToAdd = ""
        FundAmount.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.toString().let {
                    it?.let {
                        FundAmountToAdd = it
                    }
                }
            }

        })

        FundsAddFiveHund.setOnClickListener {
            changeBackground()
            ButtonBackgroundChange.ChangeBackgound(FundsAddFiveHund,this)
            var modifyAmount = if(FundAmount.text.toString().isEmpty()) "500" else (FundAmount.text.toString().toInt())+500
            FundAmount.text = Editable.Factory.getInstance().newEditable(modifyAmount.toString())
        }

        FundsAddThousand.setOnClickListener {
            changeBackground()
            ButtonBackgroundChange.ChangeBackgound(FundsAddThousand,this)
            var modifyAmount = if(FundAmount.text.toString().isEmpty()) "1000" else (FundAmount.text.toString().toInt())+1000
            FundAmount.text = Editable.Factory.getInstance().newEditable(modifyAmount.toString())
        }

        FundsAddFiveThousand.setOnClickListener {
            changeBackground()
            ButtonBackgroundChange.ChangeBackgound(FundsAddFiveThousand,this)
            var modifyAmount = if(FundAmount.text.toString().isEmpty()) "5000" else (FundAmount.text.toString().toInt())+5000
            FundAmount.text = Editable.Factory.getInstance().newEditable(modifyAmount.toString())
        }

        FundsAddTenThousand.setOnClickListener {
            changeBackground()
            var modifyAmount = if(FundAmount.text.toString().isEmpty()) "10000" else (FundAmount.text.toString().toInt())+10000
            ButtonBackgroundChange.ChangeBackgound(FundsAddTenThousand,this)
            FundAmount.text = Editable.Factory.getInstance().newEditable(modifyAmount.toString())
        }


        xPayBtn.setOnClickListener {
            val intent : Intent = Intent(this, FinalPayment::class.java)
            if(FundAmountToAdd == ""){
                Toast.makeText(this,"Amount should not be zero",Toast.LENGTH_SHORT).show()
            }else {
                intent.putExtra("FUNDAMOUNT", FundAmountToAdd)
                startActivity(intent)
            }
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

    fun changeBackground(){
        FundsAddFiveHund.setBackgroundResource(R.drawable.buy_sell_background_grey)
        FundsAddFiveHund.setTextColor(resources.getColor(R.color.black))
        FundsAddThousand.setBackgroundResource(R.drawable.buy_sell_background_grey)
        FundsAddThousand.setTextColor(resources.getColor(R.color.black))
        FundsAddFiveThousand.setBackgroundResource(R.drawable.buy_sell_background_grey)
        FundsAddFiveThousand.setTextColor(resources.getColor(R.color.black))
        FundsAddTenThousand.setBackgroundResource(R.drawable.buy_sell_background_grey)
        FundsAddTenThousand.setTextColor(resources.getColor(R.color.black))
    }

}