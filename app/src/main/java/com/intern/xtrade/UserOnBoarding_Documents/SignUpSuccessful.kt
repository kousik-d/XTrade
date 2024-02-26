package com.intern.xtrade.UserOnBoarding_Documents

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R

class SignUpSuccessful : AppCompatActivity() {

    private lateinit var animationView: LottieAnimationView
    lateinit var startTradingBtn : AppCompatButton
    lateinit var sharedPreferences :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_successful)

        sharedPreferences = getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        animationView = findViewById(R.id.animation_view)
        startTradingBtn = findViewById(R.id.SignUpCompatButton)
        sharedPreferences.edit().putInt("current_step", 0).apply()
        startTradingBtn.setOnClickListener {

            ApxorSDK.logAppEvent("Start_tradingnow_clicked")

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        animationView = findViewById(R.id.animation_view)
        animationView.setAnimation("animationCompleted.json")
        animationView.playAnimation()

    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Onboarding_completed")
    }

    override fun onBackPressed() {
        if(false) {
            super.onBackPressed()
        }
    }
}