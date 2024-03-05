package com.intern.xtrade.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R
import com.intern.xtrade.wishList.WishlistManager

class LoginActivity : AppCompatActivity() {
    lateinit var emailEdt : EditText
    lateinit var passEdt : EditText
    lateinit var loginBtn : Button
    lateinit var signUptext : TextView
//    lateinit var loginsharedPreferences :SharedPreferences

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
                WishlistManager.login(this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show()
            }
        }
    }

}