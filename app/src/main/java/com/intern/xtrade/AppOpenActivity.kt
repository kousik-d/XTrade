package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.InitalSignUp.Signup
import com.intern.xtrade.Login.LoginUserID

class AppOpenActivity : AppCompatActivity() {

    lateinit var OpenDematBtn : Button
    lateinit var LoginText : TextView

    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("App_Open_Activity")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_open)

        OpenDematBtn = findViewById(R.id.AppOpenDematAccount)
        LoginText = findViewById(R.id.AppOpenLoginBtn)
        LoginText.setOnClickListener {
            val intent = Intent(this, LoginUserID::class.java)
            startActivity(intent)
        }
        OpenDematBtn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)

            ApxorSDK.logAppEvent("Opendemtaccount_clicked")

            startActivity(intent)
        }

        LoginText.setOnClickListener {
            val intent = Intent(this, LoginUserID::class.java)

            ApxorSDK.logAppEvent("Login_clicked")

            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        if(false) {
            super.onBackPressed()
        }
    }
}