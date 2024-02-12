package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AppOpenActivity : AppCompatActivity() {

    lateinit var OpenDematBtn : Button
    lateinit var LoginText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_open)

        OpenDematBtn = findViewById(R.id.AppOpenDematAccount)
        LoginText = findViewById(R.id.AppOpenLoginBtn)
        OpenDematBtn.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }

        LoginText.setOnClickListener {

        }

    }
}