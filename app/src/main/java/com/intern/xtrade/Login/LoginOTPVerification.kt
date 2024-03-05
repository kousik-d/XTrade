package com.intern.xtrade.Login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R

class LoginOTPVerification : AppCompatActivity() {

    lateinit var loginOtpUserId : TextView
    lateinit var loginOtpEdit : ImageView
    lateinit var loginOtpConfirm : AppCompatButton
    private lateinit var otpFields: Array<EditText>
    lateinit var loginResendOtpText : TextView
    lateinit var loginResendOtpButton : AppCompatButton
    lateinit var loginCountDown : TextView
    lateinit var sharedPreferences : SharedPreferences

    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("Login_otp_verification")
    }


    var isOtpEntered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_otpverification)

        loginOtpUserId = findViewById(R.id.loginOtp_userID)
        loginOtpEdit = findViewById(R.id.loginOtp_edit)
        loginOtpConfirm = findViewById(R.id.loginOtp_confirm)
        loginResendOtpText = findViewById(R.id.LoginOtpResendOtpTextView)
        loginResendOtpButton = findViewById(R.id.loginOtp_resendOtp)
        loginCountDown = findViewById(R.id.loginOtpVerification_CountDown)

        object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                Log.i("TIMETODO","${millisUntilFinished}")
                loginCountDown.text = "00:${millisUntilFinished/1000}"
                loginResendOtpButton.isClickable = false
            }

            override fun onFinish() {
                loginResendOtpText.visibility = TextView.INVISIBLE
                loginCountDown.visibility = TextView.INVISIBLE
                loginResendOtpButton.isClickable = true
                loginResendOtpButton.setBackgroundColor(resources.getColor(R.color.card_blue))
            }
        }.start()


        val UserIdToDisplay = intent.getStringExtra("USERID")

        otpFields = arrayOf(
            findViewById(R.id.loginOtp_otp1),
            findViewById(R.id.loginOtp_otp2),
            findViewById(R.id.loginOtp_otp3),
            findViewById(R.id.loginOtp_otp4),
            findViewById(R.id.loginOtp_otp5),
            findViewById(R.id.loginOtp_otp6)
        )
        setupOtpFields()


        val emailOtp = StringBuilder()
        for (i in 0 until otpFields.size) {
            emailOtp.append(otpFields[i].text.toString())
        }


        loginOtpUserId.text = UserIdToDisplay

        loginOtpConfirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loginOtpEdit.setOnClickListener {
            finish()
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
                            isOtpEntered = false
                            loginOtpConfirm.setBackgroundColor(resources.getColor(R.color.grey))
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

        }
    }

    private fun moveFocusToNextField(currentIndex: Int) {
        if(currentIndex == otpFields.size -1){
            isOtpEntered = true
            loginOtpConfirm.setBackgroundColor(resources.getColor(R.color.card_blue))
        }else{
            isOtpEntered = false
            loginOtpConfirm.setBackgroundColor(resources.getColor(R.color.grey))
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
        isOtpEntered = false
        loginOtpConfirm.setBackgroundColor(resources.getColor(R.color.grey))
        if (currentIndex > 0) {
            otpFields[currentIndex - 1].requestFocus()
        }
    }
}