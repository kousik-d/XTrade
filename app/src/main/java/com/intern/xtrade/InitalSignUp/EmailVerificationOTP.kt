package com.intern.xtrade.InitalSignUp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.R
import com.intern.xtrade.UserDetails

class EmailVerificationOTP : AppCompatActivity() {
    private lateinit var otpFields: Array<EditText>
    private lateinit var confirmButton: Button
    lateinit var EmailToDisplay : TextView
    lateinit var EmailEdit : ImageView
    lateinit var EmailBack : ImageView
    lateinit var EmailResendOtp: TextView
    lateinit var EmailOtpCountDown : TextView
    lateinit var EmailResendText : TextView
    lateinit var EmailResendOtpButton : AppCompatButton
    var isOtpEntered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification_otp)
        val emailid = intent.getStringExtra("EMAILID")
        EmailToDisplay = findViewById(R.id.emailVerificationOTP_email)
        EmailToDisplay.text = emailid
        EmailEdit = findViewById(R.id.emailVerificationOTP_edit)
        EmailBack = findViewById(R.id.emailVerificationOTP_back)
        EmailResendOtp = findViewById(R.id.emailVerificationOTP_email)
        EmailResendText = findViewById(R.id.ResendCountDownText)
        EmailOtpCountDown = findViewById(R.id.emailVerification_Timer)
        EmailResendOtpButton = findViewById(R.id.emailVerificationOTP_resendOTP)
        EmailResendOtpButton.isClickable = false

        object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                Log.i("TIMETODO","${millisUntilFinished}")
                EmailOtpCountDown.text = "00:${millisUntilFinished/1000}"
                EmailResendOtpButton.isClickable = false
            }

            override fun onFinish() {
                EmailResendText.visibility = TextView.INVISIBLE
                EmailOtpCountDown.visibility = TextView.INVISIBLE
                EmailResendOtpButton.isClickable = true
                EmailResendOtpButton.setBackgroundColor(resources.getColor(R.color.card_blue))
            }
        }.start()

        EmailResendOtpButton.setOnClickListener {

            ApxorSDK.logAppEvent("Resend_OTP_Clicked")

            Toast.makeText(this,"OTP sent Successfully", Toast.LENGTH_SHORT).show()
            EmailResendOtpButton.visibility = AppCompatButton.INVISIBLE
        }




        EmailEdit.setOnClickListener {
            finish()
        }

        EmailBack. setOnClickListener {
            finish()
        }
        emailid?.let {
            Log.i("EMAILID",it)
            EmailToDisplay.text = it
        }
        // = if (emailid.isNullOrEmpty()) "sample@gmail.com" else emailid
        confirmButton = findViewById(R.id.emailVerificationOTP_confirm)

        confirmButton.setOnClickListener {
            if(isOtpEntered) {

                val attrs1 = Attributes();
                attrs1.putAttribute("Source","email")
                ApxorSDK.logAppEvent("OTP_Submitted",attrs1)

                val intent = Intent(this, UserDetails::class.java)
                startActivity(intent)
            }
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
            confirmButton.background = ColorDrawable(ContextCompat.getColor(this, R.color.card_blue))
        }
    }

    private fun setupOtpFields() {
        for (i in 0 until otpFields.size) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    s?.let {
                        if(it.isEmpty()){
                            confirmButton.setBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
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
        if(currentIndex == otpFields.size -1){
            isOtpEntered = true
            confirmButton.setBackgroundColor(resources.getColor(R.color.card_blue))
        }else{
            isOtpEntered = false
            confirmButton.setBackgroundColor(resources.getColor(R.color.grey))
        }
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