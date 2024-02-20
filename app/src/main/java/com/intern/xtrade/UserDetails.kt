package com.intern.xtrade


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.intern.xtrade.UserOnBoarding.BankDetails
import com.intern.xtrade.UserOnBoarding.PANDetails
import com.intern.xtrade.UserOnBoarding_Documents.DocumentsFirst
import com.intern.xtrade.UserOnBoarding_Documents.DocumentsSecond
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails2
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails3
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails4
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails5
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails6
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails7

class UserDetails : AppCompatActivity() {
    lateinit var loadFrameLayout : FrameLayout
    lateinit var UserDetailsTabOne : View
    lateinit var UserDetailsBankStep : ImageView
    lateinit var UserDetailsTabTwo : View
    lateinit var UserDetailsTabThree : View
    lateinit var UserDetailsDocumentsStep : ImageView
    lateinit var UserDetailsPersonalStep :ImageView
    lateinit var UserDetailsPanStep : ImageView
    lateinit var UserDetailsBankTextView : TextView
    lateinit var UserDetailsPersonalTextView :TextView
    lateinit var UserDetailsBackBtn : ImageView
    lateinit var sharedPreferences: SharedPreferences

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        sharedPreferences = getSharedPreferences("APP_STATUS",Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_status",11).apply()

        loadFrameLayout = findViewById(R.id.userDetails_frame)
        UserDetailsTabOne = findViewById(R.id.userDetails_tab1)
        UserDetailsBankStep = findViewById(R.id.userDetails_step2)
        UserDetailsTabTwo = findViewById(R.id.userDetails_tab2)
        UserDetailsPersonalStep = findViewById(R.id.userDetails_step3)
        UserDetailsPanStep = findViewById(R.id.userDetails_step1)
        UserDetailsBankTextView = findViewById(R.id.userDetails_text2)
        UserDetailsPersonalTextView = findViewById(R.id.userDetails_text3)
        UserDetailsBackBtn = findViewById(R.id.userDetails_back)
        UserDetailsTabThree = findViewById(R.id.userDetails_tab3)
        UserDetailsDocumentsStep = findViewById(R.id.userDetails_step4)

        UserDetailsBackBtn.setOnClickListener {
            finishAffinity()
        }

        val FragmentToLoad = intent.getIntExtra("FRAGMENTTOLOAD",0)

        when(FragmentToLoad){
            1 ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.userDetails_frame, PANDetails())
                    .commit()
                count++
            }
            2-> {
                val bankdetails = BankDetails()
                this.LoadProgress(bankdetails)
                this.onNextButtonClick(bankdetails)
            }
            3-> {
                val persondetails = PersonalDetails()
                this.LoadProgress(persondetails)
                this.onNextButtonClick(persondetails)
            }
            4-> {
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails2())
            }
            5->{
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails3())
            }
            6 ->{
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails4())
            }
            7->{
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails5())
            }
            8->{
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails6())
            }
            9-> {
                this.LoadProgress(PersonalDetails())
                this.onNextButtonClick(PersonalDetails7())
            }
            10->{
                val documentFirst = DocumentsFirst()
                this.LoadProgress(documentFirst)
                this.onNextButtonClick(documentFirst)
            }
            11 ->{
                this.onNextButtonClick(DocumentsSecond())
            } else -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.userDetails_frame, PANDetails())
                .commit()
            count++
            }
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.userDetails_frame, fragment)
        transaction.addToBackStack(null)  // Add the transaction to the back stack
        transaction.commit()
    }
    // Function to handle "Continue" button click
    fun onNextButtonClick(currentFragment: Fragment) {
        when (currentFragment) {
            is BankDetails -> loadFragment(BankDetails())
            is PersonalDetails -> loadFragment(PersonalDetails())
            is PersonalDetails2 -> loadFragment(PersonalDetails2())
            is PersonalDetails3 -> loadFragment(PersonalDetails3())
            is PersonalDetails4 -> loadFragment(PersonalDetails4())
            is PersonalDetails5 -> loadFragment(PersonalDetails5())
            is PersonalDetails6 -> loadFragment(PersonalDetails6())
            is PersonalDetails7 -> loadFragment(PersonalDetails7())
            is DocumentsFirst -> loadFragment(DocumentsFirst())
            is DocumentsSecond -> loadFragment(DocumentsSecond())
        }
    }

    fun LoadProgress(currentFragment: Fragment){
        when(currentFragment){
            is BankDetails -> {
                count++
                Log.i("USERBANKDETAILS","HERE")
                UserDetailsPanStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabOne.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsBankStep.setImageResource(R.drawable.details_man)
            }
            is PersonalDetails ->{
                count++
                UserDetailsPanStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabOne.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsBankTextView.setTextColor(resources.getColor(R.color.card_blue))
                UserDetailsBankStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabTwo.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsPersonalStep.setImageResource(R.drawable.details_man)
            }
            is DocumentsFirst ->{
                count++
                UserDetailsPanStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabOne.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsBankTextView.setTextColor(resources.getColor(R.color.card_blue))
                UserDetailsBankStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabTwo.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsTabThree.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsPersonalTextView.setTextColor(resources.getColor(R.color.card_blue))
                UserDetailsPersonalStep.setImageResource(R.drawable.details_checked)
                UserDetailsDocumentsStep.setImageResource(R.drawable.details_man)
            }
        }
    }

    fun createDialog() {
        val dialogview = LayoutInflater.from(this).inflate(R.layout.dialog_box, null)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setView(dialogview)
        }
        val alertdialog = builder.show()
        val ybtn = alertdialog.findViewById<Button>(R.id.dialog_continue)
        val nbtn = alertdialog.findViewById<Button>(R.id.dialog_close)
        val Text = alertdialog.findViewById<TextView>(R.id.dialog_steps)
        Text?.text = "${4-count}"
        ybtn?.setOnClickListener {
            alertdialog.dismiss()
        }
        nbtn?.setOnClickListener {
            alertdialog.dismiss()
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        if(false) {
            super.onBackPressed()
        }
    }

}