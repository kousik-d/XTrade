package com.intern.xtrade.RegularAndAMO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.apxor.androidsdk.core.ApxorSDK
import com.google.android.material.tabs.TabLayout
import com.intern.xtrade.Adapters.FragmentPageAdapter
import com.intern.xtrade.Adapters.FragmentPageAdapterSell
import com.intern.xtrade.R

class SellActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var adapter : FragmentPageAdapterSell
    lateinit var purchaseStockBackBtn : ImageView
    //    lateinit var BuyToolBar : Toolbar
    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Sell_Activity")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tab_layout)
        purchaseStockBackBtn = findViewById(R.id.BuyActivityBackBtn)
        purchaseStockBackBtn.setOnClickListener {
            finish()
        }
        val stockId= intent.getIntExtra("SELLSTOCKID",1)
        val stockPrice = intent.getFloatExtra("SELLSTOCKPRICE",0.0f)
        sellRegular.sellStockId = stockId
        sellRegular.sellStockPrice = stockPrice
        adapter = FragmentPageAdapterSell(supportFragmentManager ,lifecycle)
        viewPager.adapter = adapter
        Log.i("TABCHECK","${10}")

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    Log.i("TABCHECK","${it.position}")
                    viewPager.currentItem = it.position;
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}