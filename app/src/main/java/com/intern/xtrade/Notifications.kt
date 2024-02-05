package com.intern.xtrade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class Notifications : AppCompatActivity() {

    lateinit var notificationBackBtn : ImageView
    lateinit var PushNotification : SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_notifications)

        notificationBackBtn = findViewById(R.id.NotificationBackBtn)
        PushNotification = findViewById(R.id.pushSwitch)
        notificationBackBtn.setOnClickListener {
            finish()
        }
        if(PushNotification.isChecked == true){
            Toast.makeText(this,"Notification enabled",Toast.LENGTH_LONG).show()
        }

    }
}