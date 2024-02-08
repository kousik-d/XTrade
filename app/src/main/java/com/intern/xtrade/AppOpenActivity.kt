package com.intern.xtrade

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.LinearInterpolator
import android.widget.Scroller
import android.widget.TextView

class AppOpenActivity : AppCompatActivity() {
    lateinit var scrollingText :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_open)
        scrollingText = findViewById(R.id.scrollingText)

    }
}