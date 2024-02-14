package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.intern.xtrade.wishList.WishlistManager

class SplashActivity:  AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        setContentView(R.layout.actvity_splash_screen)


            Handler().postDelayed({
                onResume()
            }, 2000)
    }

    override fun onResume() {
        super.onResume()
        val LoadAct = sharedPreferences.getInt("current_step",-1)
        if(LoadAct == 0){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }
}