package com.intern.xtrade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.Fragments.PortfolioFragment
import com.intern.xtrade.Fragments.ProfileFragment
import com.intern.xtrade.Fragments.RewardsFragment

class MainActivity : AppCompatActivity() {

    private var TAG = "MAIN_ACTIVITY"

    lateinit var homeFragment: HomeFragment
    lateinit var portfolioFragment: PortfolioFragment
    lateinit var rewardsFragment: RewardsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var bottom_nav : BottomNavigationView

    //R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        portfolioFragment = PortfolioFragment()
        rewardsFragment = RewardsFragment()
        profileFragment = ProfileFragment()

        bottom_nav = findViewById(R.id.bottom_nav)
        setCurrentFragment(homeFragment)

        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG,"HOME")
                    true
                }
                R.id.nav_portfolio ->{
                    setCurrentFragment(portfolioFragment)
                    Log.i(TAG,"PORTFOLIO")
                    true
                }
                R.id.nav_rewards ->{
                    setCurrentFragment(rewardsFragment)
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
}