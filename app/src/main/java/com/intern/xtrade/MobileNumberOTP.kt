package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import org.w3c.dom.Text

class MobileNumberOTP : AppCompatActivity() {

    lateinit var MobileVerificationNumber : TextView
    lateinit var MobileVerificationScreenBack : ImageView
    lateinit var MobileNumberEdit : ImageView
    lateinit var MobileNumberContinueBtn : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_number_otp)

        MobileVerificationNumber = findViewById(R.id.mobileNumberVerification_number)
        MobileVerificationScreenBack = findViewById(R.id.mobileNumberVerification_back)
        MobileNumberEdit = findViewById(R.id.mobileNumberVerification_edit)
        MobileNumberContinueBtn = findViewById(R.id.mobileNumberVerification_confirm)

        val mobileToDisplay = intent.getStringExtra("MOBILENUMBER")
        MobileVerificationNumber.text = mobileToDisplay.toString()

        MobileVerificationScreenBack.setOnClickListener {
            finish()
        }

        MobileNumberContinueBtn.setOnClickListener {
            val intent  = Intent(this,EmailVerification::class.java)
            startActivity(intent)
        }

        MobileNumberEdit.setOnClickListener {
            finish()
        }
    }
}