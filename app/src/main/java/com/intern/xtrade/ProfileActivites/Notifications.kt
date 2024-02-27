package com.intern.xtrade.ProfileActivites

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
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
        val attrs2 = Attributes();
        attrs2.putAttribute("Notification_button_name","PushNotification")
        ApxorSDK.logAppEvent("Notification_page_clicked ",attrs2)
        PushNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                val attrs3 = Attributes();
                attrs3.putAttribute("Push_notification","ON")
                ApxorSDK.logAppEvent("Notification_button_edited",attrs3)
            }else{
                val attrs4 = Attributes();
                attrs4.putAttribute("Push_notification","OFF")
                ApxorSDK.logAppEvent("Notification_button_edited",attrs4)
            }
        }

        if(PushNotification.isChecked == true){

            Toast.makeText(this,"Notification enabled",Toast.LENGTH_LONG).show()
        }

    }
}