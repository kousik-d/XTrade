package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

class EmailVerification : AppCompatActivity() {

    lateinit var EmailVerificationContinueBtn : AppCompatButton
    lateinit var EmailVerificationBackButton : ImageView
    lateinit var EmailEditText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification)

        EmailVerificationContinueBtn = findViewById(R.id.emailVerification_continue)
        EmailVerificationBackButton = findViewById(R.id.emailVerification_back)
        EmailEditText = findViewById(R.id.emailVerification_email)

        Log.i("EMAILVERIFY","${EmailEditText.text.toString()}")

        EmailVerificationContinueBtn.setOnClickListener {
            val intent = Intent(this,EmailVerificationOTP::class.java)
            startActivity(intent)
        }

    }
}