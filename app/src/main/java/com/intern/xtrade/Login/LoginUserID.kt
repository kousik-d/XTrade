package com.intern.xtrade.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.text.isDigitsOnly
import com.intern.xtrade.InitalSignUp.Signup
import com.intern.xtrade.R

class LoginUserID : AppCompatActivity() {

    lateinit var UserIdEditText : EditText
    lateinit var WarningTextView : TextView
    lateinit var UserIdProccedBtn : AppCompatButton
    lateinit var SignUpTextView : TextView
    lateinit var loginUserIDBack : ImageView
    var isUserIdCorrect = false
    var UserID = "12345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user_id)

        UserIdEditText = findViewById(R.id.LoginUserID_Verification)
        WarningTextView = findViewById(R.id.loginUserID_warning)
        UserIdProccedBtn = findViewById(R.id.loginUserID_proceed)
        SignUpTextView = findViewById(R.id.loginUserID_signUp)
        loginUserIDBack = findViewById(R.id.loginUserID_back)

        loginUserIDBack.setOnClickListener {
            finish()
        }

        SignUpTextView.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        UserIdProccedBtn.setOnClickListener {
            if(isUserIdCorrect){
                val intent = Intent(this, LoginOTPVerification::class.java)
                intent.putExtra("USERID",UserID)
                startActivity(intent)
            }
        }


        UserIdEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
                val emailOrPhone = s.toString()
                if(emailRegex.matches(emailOrPhone) || ((emailOrPhone.isDigitsOnly() && emailOrPhone.length==10))){
                    WarningTextView.text = "Valid User Id"
                    UserID = emailOrPhone
                    WarningTextView.setTextColor(resources.getColor(R.color.green))
                    isUserIdCorrect = true
                    UserIdProccedBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
                }else{
                    isUserIdCorrect = false
                    WarningTextView.text = "*UserID doesn't exist"
                    WarningTextView.setTextColor(resources.getColor(R.color.red))
                    UserIdProccedBtn.setBackgroundColor(resources.getColor(R.color.grey))
                }

            }

        })


    }
}