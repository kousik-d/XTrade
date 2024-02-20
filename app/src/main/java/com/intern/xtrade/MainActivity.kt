package com.intern.xtrade

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.Fragments.PortfolioFragment
import com.intern.xtrade.Fragments.ProfileFragment
import com.intern.xtrade.Fragments.RewardsFragment
import com.intern.xtrade.Ideas.Idea


class MainActivity : AppCompatActivity() {

    private var TAG = "MAIN_ACTIVITY"
    lateinit var homeFragment: HomeFragment
    lateinit var ideaFragment : Idea
    lateinit var portfolioFragment: PortfolioFragment
    lateinit var rewardsFragment: RewardsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var bottom_nav : BottomNavigationView
    lateinit var sharedPreferences: SharedPreferences


    //R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = HomeFragment()
        portfolioFragment = PortfolioFragment()
        rewardsFragment = RewardsFragment()
        profileFragment = ProfileFragment()
        ideaFragment = Idea()
        val AmountDeposited = intent.getIntExtra("DEPOSITEDMONEY",0)

        Log.i(TAG,"MAINACTIVITYLOGEDIN")

        sharedPreferences = getSharedPreferences("APP_STATUS",Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step",0).apply()


        bottom_nav = findViewById(R.id.bottom_nav)
        setCurrentFragment(homeFragment)

        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG,"HOME")
                    true
                }
                R.id.nav_portfolio -> {
                    PortfolioFragment.AmountToAddToDeposit = AmountDeposited
                    setCurrentFragment(portfolioFragment)
                    Log.i(TAG,"PORTFOLIO")
                    true
                }
                R.id.nav_idea ->{
                    setCurrentFragment(ideaFragment)
                    Log.i(TAG,"REWARD")
                    true
                }
                R.id.nav_profile ->{
                    setCurrentFragment(profileFragment)
                    Log.i(TAG,"PROFILE")
                    true
                }
                else ->  false
            }
        }


    }

    fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wrapper,fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        if(false){
            super.onBackPressed()
        }
    }


}