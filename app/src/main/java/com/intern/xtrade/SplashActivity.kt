package com.intern.xtrade

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent);
        },3000)
    }
}