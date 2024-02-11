package com.intern.xtrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_personal_details)
//        val mobileNumber: EditText = findViewById(R.id.signup_mobileNumber)
//
//// Add a text changed listener to the mobile number EditText
//        mobileNumber.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // Check if the length is divisible by 4 (every three digits plus one slash)
//                if ((s?.length ?: 0) % 4 == 0 && start > 0 && s?.get(start - 1) != '/') {
//                    // Insert a slash at the current cursor position
//                    mobileNumber.text?.insert(start, "/")
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {}
//        })




    }


}