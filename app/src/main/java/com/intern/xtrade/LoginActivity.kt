package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var emailEdt : EditText
    lateinit var passEdt : EditText
    lateinit var loginBtn : Button
    lateinit var signUptext : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEdt = findViewById(R.id.userEmailId);
        passEdt = findViewById(R.id.userPasswordId);
        loginBtn = findViewById(R.id.loginInButton);
        signUptext = findViewById(R.id.sign_up);
        signUptext.setOnClickListener {
            //Do Something
        }
        loginBtn.setOnClickListener {
            if(emailEdt.text.toString().isNotEmpty() && passEdt.text.toString().isNotEmpty()){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show()
            }
        }
    }
}