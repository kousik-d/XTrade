package com.intern.xtrade.ProfileActivites

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.intern.xtrade.R

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