package com.intern.xtrade

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.intern.xtrade.wishList.WishlistManager

class SplashActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_selfie_capture)
//            Handler().postDelayed({
//                val intent = Intent(this, Signup::class.java)
//                startActivity(intent);
//            }, 2000)
    }
}