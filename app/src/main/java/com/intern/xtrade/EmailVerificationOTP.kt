package com.intern.xtrade

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class EmailVerificationOTP : AppCompatActivity() {
    private lateinit var otpFields: Array<EditText>
    private lateinit var confirmButton: Button
    lateinit var EmailToDisplay : TextView
    lateinit var EmailEdit : ImageView
    lateinit var EmailBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification_otp)
        val emailid = intent.getStringExtra("EMAILID")
        EmailToDisplay = findViewById(R.id.emailVerificationOTP_email)
        EmailEdit = findViewById(R.id.emailVerificationOTP_edit)
        EmailBack = findViewById(R.id.emailVerificationOTP_back)

        EmailEdit.setOnClickListener {
            finish()
        }

        EmailBack. setOnClickListener {
            finish()
        }

        EmailToDisplay.text = if (emailid.isNullOrEmpty()) "sample@gmail.com" else emailid
        confirmButton = findViewById(R.id.emailVerificationOTP_confirm)

        confirmButton.setOnClickListener {
            val intent = Intent(this,UserDetails::class.java)
            startActivity(intent)
        }

        otpFields = arrayOf(
            findViewById(R.id.emailVerification_otp1),
            findViewById(R.id.emailVerification_otp2),
            findViewById(R.id.emailVerification_otp3),
            findViewById(R.id.emailVerification_otp4),
            findViewById(R.id.emailVerification_otp5),
            findViewById(R.id.emailVerification_otp6)
        )

        setupOtpFields()



        val emailOtp = StringBuilder()
        for (i in 0 until otpFields.size) {

            emailOtp.append(otpFields[i].text.toString())
        }



        if (emailOtp.length == 6) {
            confirmButton.isClickable = true
            confirmButton.background = ColorDrawable(ContextCompat.getColor(this, R.color.card_blue))
        }
    }

    private fun setupOtpFields() {
        for (i in 0 until otpFields.size) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {

                        moveFocusToNextField(i)
                    }
                    updateFieldBackground(i)
                }
            })

            otpFields[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && otpFields[i].text.isNullOrEmpty()) {
                    moveFocusToPreviousField(i)
                    true
                } else {
                    false
                }
            }
            Log.d("vikram", "Length of email OTP: ${otpFields[i].text}")

        }
    }

    private fun moveFocusToNextField(currentIndex: Int) {
        if (currentIndex < otpFields.size - 1) {
            otpFields[currentIndex + 1].requestFocus()
        }
    }

    private fun updateFieldBackground(index: Int) {
        val editText = otpFields[index]
        val digit = editText.text.toString().toIntOrNull()

        if (digit != null && digit in 0..9) {
            editText.setBackgroundResource(R.drawable.edit_text_background_blue)
        } else {
            editText.setBackgroundResource(R.drawable.buy_sell_background_grey)
        }
    }

    private fun moveFocusToPreviousField(currentIndex: Int) {
        if (currentIndex > 0) {
            otpFields[currentIndex - 1].requestFocus()
        }
    }
}