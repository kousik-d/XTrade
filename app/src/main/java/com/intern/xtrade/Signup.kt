package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import java.security.AccessController.getContext

class Signup : AppCompatActivity() {

    var isMobileNumberValid = false
    lateinit var continueBtn : Button
    lateinit var TermsCheckBox : CheckBox
    lateinit var SignUpMobileNumber : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var MobileNumber : String = "123456789"

        SignUpMobileNumber = findViewById(R.id.signup_mobileNumber)
        val relativeLayout = findViewById<RelativeLayout>(R.id.SignUpContinueRelative)
        var mobileNumberWarning : TextView = findViewById(R.id.signup_mobileNumberWarning)


        TermsCheckBox = findViewById(R.id.signup_checkBox)
        continueBtn = relativeLayout.findViewById(R.id.signup_continue)

        continueBtn.isClickable = true

        continueBtn.setOnClickListener {
            val intent = Intent(this, MobileNumberOTP::class.java)
            intent.putExtra("MOBILENUMBER", MobileNumber)
            startActivity(intent)
        }


        SignUpMobileNumber.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val Numbertext = s.toString()
                if(Numbertext.length<10)
                {
                    mobileNumberWarning.text = "Enter a valid Mobile number"
                    mobileNumberWarning.setTextColor(ContextCompat.getColor(this@Signup,R.color.red))
                }
                else
                {
                    isMobileNumberValid = true
                    MobileNumber = s.toString()
                    mobileNumberWarning.text = "Mobile number is valid"
                    mobileNumberWarning.setTextColor(ContextCompat.getColor(this@Signup,R.color.green))
                }
            }
        })

//
//
//        TermsCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            if(isChecked && isMobileNumberValid){
//                Log.i("KOUSIKDASAINCHECK","${isMobileNumberValid}")
//                continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
//            }else{
//
//            }
//        }
//        Log.i("KOUSIKDASA","${continueBtn.isClickable}")





    }

}