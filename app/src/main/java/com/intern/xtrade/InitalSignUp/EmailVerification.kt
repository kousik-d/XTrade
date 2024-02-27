package com.intern.xtrade.InitalSignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.R

class EmailVerification : AppCompatActivity() {

    lateinit var EmailVerificationContinueBtn : AppCompatButton
    lateinit var EmailVerificationBackButton : ImageView
    lateinit var EmailEditText : EditText
    lateinit var Emailwarning : TextView
    var canContinue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification)

        var emailToSend = "Xtrade@apxor.com"

        EmailVerificationContinueBtn = findViewById(R.id.emailVerification_continue)
        EmailVerificationBackButton = findViewById(R.id.emailVerification_back)
        EmailEditText = findViewById(R.id.emailVerification_email)
        Emailwarning = findViewById(R.id.email_emailIdWarning)

        EmailEditText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("EMAILKOUSIK","${s.toString()}")
            }

            override fun afterTextChanged(s: Editable?) {
                val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
                val email = s.toString()
//                Log.i("EMAILKOUSIK","${email}")
                if(emailRegex.matches(email)){
                    emailToSend = email
                    Emailwarning.text = "Valid Email"
                    Emailwarning.setTextColor(resources.getColor(R.color.green))
                    canContinue = true
                    changeBackGround()
                }else{
                    canContinue = false
                    Emailwarning.text = "Enter a Valid Email"
                    EmailVerificationContinueBtn.setBackgroundColor(resources.getColor(R.color.grey))
                    Emailwarning.setTextColor(resources.getColor(R.color.red))
                }
            }

        })

        EmailVerificationContinueBtn.setOnClickListener {
            if(canContinue) {

                val attrs1 = Attributes()
                attrs1.putAttribute("Email",EmailEditText.text.toString())
                ApxorSDK.logAppEvent("Email_Entered",attrs1)

                val intent = Intent(this, EmailVerificationOTP::class.java)
                intent.putExtra("EMAILID",emailToSend)
                startActivity(intent)
            }
        }

    }
    fun changeBackGround(){
        EmailVerificationContinueBtn.isClickable = true
        EmailVerificationContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }

    override fun onBackPressed() {
        if(false){
            super.onBackPressed()
        }
    }
}