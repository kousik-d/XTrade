package com.intern.xtrade

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class Signup : AppCompatActivity() {


    lateinit var continueButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        continueButton = findViewById(R.id.signup_continue)
        continueButton.setOnClickListener {

            val intent = Intent(this, MobileNumberOTP::class.java)
            startActivity(intent)

            Log.i("vikram", "button clicked")

        }

    }

}