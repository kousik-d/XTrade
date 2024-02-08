package com.intern.xtrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var mobileNumber : EditText = findViewById(R.id.signup_mobileNumber)
        var mobileNumberWarning : TextView = findViewById(R.id.signup_mobileNumberWarning)
        var checkBox : CheckBox = findViewById(R.id.signup_checkBox)
        var continueBtn : Button = findViewById(R.id.signup_continue)


        if(mobileNumber.text.length<10)
        {
            mobileNumberWarning.text = "Enter a valid Mobile number"
            mobileNumberWarning.setTextColor(R.color.red)
        }
        else
        {
            mobileNumberWarning.text = "Mobile number is valid"
            mobileNumberWarning.setTextColor(R.color.green)
        }

    }
}