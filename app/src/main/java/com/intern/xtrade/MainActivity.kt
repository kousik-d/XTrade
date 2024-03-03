package com.intern.xtrade

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    lateinit var sharedPrefereneForUserAttributes : SharedPreferences
    lateinit var sharedPreferences1: SharedPreferences
     var stocksFromDb : List<StockInfo> = listOf()


    override fun onResume() {
        super.onResume()
        val addFundCount = sharedPrefereneForUserAttributes.getInt("ADDFUNDCOUNT",0)
        val withDrawCount = sharedPrefereneForUserAttributes.getInt("WITHDRAWFUNDCOUNT",0)
        val StockBuyedCount = sharedPrefereneForUserAttributes.getInt("STOCKBUYCOUNT",3)
        val StockSellCount = sharedPrefereneForUserAttributes.getInt("STOCKSELLCOUNT",0)
        val StockBuy = sharedPrefereneForUserAttributes.getFloat("STOCKBUYMoney",0.0f)
        val StockSell = sharedPrefereneForUserAttributes.getFloat("STOCKSELLMoney",0.0f)
        val verification = sharedPrefereneForUserAttributes.getBoolean("USERVERIFY",false)
        val name = sharedPrefereneForUserAttributes.getString("USERNAME","Alice")
        val witdrawAmount = sharedPrefereneForUserAttributes.getInt("WITHDRAWAMOUNTIN",0)
        val fundsAmount = sharedPreferences1.getInt("AVAILABLEINR",0)

        Log.i("STOCKSPLAS","${StockSell}-${StockSellCount} ${verification}")

        val attrs3 = Attributes()
        attrs3.putAttribute("Number_of_Funds_added",addFundCount)
        attrs3.putAttribute("Number_of_Funds_withdrawed",withDrawCount)
        attrs3.putAttribute("Verification",verification)
        attrs3.putAttribute("Number_of_Stocks_buyed",StockBuyedCount)
        attrs3.putAttribute("Number_of_Stocks_selled",StockSellCount)
        attrs3.putAttribute("Stocks_buyed",StockBuy)
        attrs3.putAttribute("Stocks_selled",StockSell)
        attrs3.putAttribute("Funds_added",fundsAmount)
        attrs3.putAttribute("Funds_withdrawed",witdrawAmount)

        ApxorSDK.setUserCustomInfo(attrs3)
    }

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
        sharedPreferences1 = getSharedPreferences("MONEY",Context.MODE_PRIVATE)
        sharedPrefereneForUserAttributes = getSharedPreferences("USERATTR",Context.MODE_PRIVATE)


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