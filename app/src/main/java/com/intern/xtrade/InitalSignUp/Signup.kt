package com.intern.xtrade.InitalSignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.intern.xtrade.R

class Signup : AppCompatActivity() {

    lateinit var continueBtn : Button
    lateinit var TermsCheckBox : CheckBox
    lateinit var SignUpMobileNumber : EditText
    lateinit var name : EditText
    lateinit var nameWarning : TextView
    var isNameOk = false
    var isMobileNumberOk = false
    var isTermsOk = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var MobileNumber : String = "123456789"

        name = findViewById(R.id.signup_name)
        nameWarning = findViewById(R.id.signup_NameWarning)

        SignUpMobileNumber = findViewById(R.id.signup_mobileNumber)
        val relativeLayout = findViewById<RelativeLayout>(R.id.SignUpContinueRelative)
        var mobileNumberWarning : TextView = findViewById(R.id.signup_mobileNumberWarning)


        TermsCheckBox = findViewById(R.id.signup_checkBox)
        continueBtn = relativeLayout.findViewById(R.id.signup_continue)


        continueBtn.setOnClickListener {
            if(isMobileNumberOk && isTermsOk && isNameOk) {
                val intent = Intent(this, MobileNumberOTP::class.java)
                intent.putExtra("MOBILENUMBER", MobileNumber)
                startActivity(intent)
            }
        }

        name.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var fullname = s.toString()
                if(fullname.isNotEmpty() && fullname !=" ")
                {
                    nameWarning.visibility = TextView.INVISIBLE
                    isNameOk = true
                    if(isMobileNumberOk && isTermsOk){
                        continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
                    }
                }
                else
                {
                    nameWarning.visibility = TextView.VISIBLE
                    isNameOk = false
                }
            }

        })


        SignUpMobileNumber.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val Numbertext = s.toString()
                if(Numbertext.length<10)
                {
                    isMobileNumberOk= false
                    continueBtn.setBackgroundColor(resources.getColor(R.color.grey))
                    mobileNumberWarning.text = "*Mobile Number should be atleast 10 digits"
                    mobileNumberWarning.setTextColor(ContextCompat.getColor(this@Signup,
                        R.color.red
                    ))
                }
                else
                {
                    isMobileNumberOk =true
                    if(isTermsOk && isNameOk){
                        continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
                    }
                    MobileNumber = s.toString()
                    mobileNumberWarning.text = "Mobile number is valid"
                    mobileNumberWarning.setTextColor(ContextCompat.getColor(this@Signup,
                        R.color.green
                    ))
                }
            }
        })

        TermsCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                isTermsOk = true
                if(isMobileNumberOk && isNameOk){
                    continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
                }
            }else{
                isTermsOk = false
                continueBtn.setBackgroundColor(resources.getColor(R.color.grey))
            }
        }

//
//
//        TermsCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            if(isChecked && isMobileNumberValid){
//                Log.i("KOUSIKDASAINCHECK","${isMobileNumberValid}")
//                continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
//            }else{
//
//            }
//        }
//        Log.i("KOUSIKDASA","${continueBtn.isClickable}")

    }
    fun changeButtonBackground(){
        continueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }

}