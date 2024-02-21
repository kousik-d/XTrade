package com.intern.xtrade

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.Fragments.PortfolioFragment
import com.intern.xtrade.Fragments.ProfileFragment
import com.intern.xtrade.Fragments.RewardsFragment
import com.intern.xtrade.Ideas.Idea
import com.intern.xtrade.Repositories.IPORepository
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import java.sql.Time
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var TAG = "MAIN_ACTIVITY"
    lateinit var homeFragment: HomeFragment
    lateinit var ideaFragment : Idea
    lateinit var portfolioFragment: PortfolioFragment
    lateinit var rewardsFragment: RewardsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var bottom_nav : BottomNavigationView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var stockRepository : StockRepository
     var stocksFromDb : List<StockInfo> = listOf()

    //R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = HomeFragment()
        portfolioFragment = PortfolioFragment()
        rewardsFragment = RewardsFragment()
        profileFragment = ProfileFragment()
        ideaFragment = Idea()
        sharedPreferences = getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)

        sharedPreferences.edit().putInt("current_step", 0).apply()
        val AmountDeposited = intent.getIntExtra("DEPOSITEDMONEY",0)

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