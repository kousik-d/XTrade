package com.intern.xtrade


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.intern.xtrade.UserOnBoarding.PersonalDetails
import com.intern.xtrade.UserOnBoarding.PersonalDetails2
import com.intern.xtrade.UserOnBoarding.PersonalDetails3
import com.intern.xtrade.UserOnBoarding.PersonalDetails4
import com.intern.xtrade.UserOnBoarding.PersonalDetails5
import com.intern.xtrade.UserOnBoarding.PersonalDetails6
import com.intern.xtrade.UserOnBoarding.PersonalDetails7

class UserDetails : AppCompatActivity() {
    lateinit var loadFrameLayout : FrameLayout
    lateinit var UserDetailsTabOne : View
    lateinit var UserDetailsBankStep : ImageView
    lateinit var UserDetailsTabTwo : View
    lateinit var UserDetailsPersonalStep :ImageView
    lateinit var UserDetailsPanStep : ImageView
    lateinit var UserDetailsBankTextView : TextView
    lateinit var UserDetailsPersonalTextView :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        loadFrameLayout = findViewById(R.id.userDetails_frame)
        UserDetailsTabOne = findViewById(R.id.userDetails_tab1)
        UserDetailsBankStep = findViewById(R.id.userDetails_step2)
        UserDetailsTabTwo = findViewById(R.id.userDetails_tab2)
        UserDetailsPersonalStep = findViewById(R.id.userDetails_step3)
        UserDetailsPanStep = findViewById(R.id.userDetails_step1)
        UserDetailsBankTextView = findViewById(R.id.userDetails_text2)
        UserDetailsPersonalTextView = findViewById(R.id.userDetails_text3)

        supportFragmentManager.beginTransaction()
            .replace(R.id.userDetails_frame,PANDetails())
            .commit()
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
        }
    }

    fun LoadProgress(currentFragment: Fragment){
        when(currentFragment){
            is BankDetails -> {
                UserDetailsPanStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabOne.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsBankStep.setImageResource(R.drawable.details_man)
            }
            is PersonalDetails ->{
                UserDetailsBankTextView.setTextColor(resources.getColor(R.color.card_blue))
                UserDetailsBankStep.setImageResource(R.drawable.details_checked)
                UserDetailsTabTwo.setBackgroundResource(R.drawable.details_tab_after)
                UserDetailsPersonalStep.setImageResource(R.drawable.details_man)
            }
        }
    }
}