package com.intern.xtrade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


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