package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.intern.xtrade.wishList.WishlistManager

class SplashActivity:  AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        setContentView(R.layout.actvity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({

        }, 3000)

    }

    private fun doNothing() {

    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val LoadAct = sharedPreferences.getInt("current_step",-1)
            if(LoadAct == 0){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else if(arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10,11).contains(LoadAct)){
                val intent = Intent(this,UserDetails::class.java)
                intent.putExtra("FRAGMENTTOLOAD",LoadAct)
                startActivity(intent)
            }
            else{
                val intent = Intent(this,Signup::class.java)
                startActivity(intent)
            }},2000)
    }
}