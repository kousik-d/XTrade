package com.intern.xtrade.ProfileActivites

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.intern.xtrade.R


class SecurityInformation : AppCompatActivity() {
    lateinit var backButtonSecurityInformation: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_security_information)

        backButtonSecurityInformation = findViewById(R.id.security_back)

        backButtonSecurityInformation.setOnClickListener {
            finish()
        }
    }
}