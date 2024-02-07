package com.intern.xtrade.RegularAndAMO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.intern.xtrade.Adapters.FragmentPageAdapter
import com.intern.xtrade.R

class Buy_activity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var adapter : FragmentPageAdapter
    lateinit var purchaseStockBackBtn : ImageView
//    lateinit var BuyToolBar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buy_activity)
         viewPager = findViewById(R.id.viewPager)
         tabLayout = findViewById(R.id.tab_layout)
         purchaseStockBackBtn = findViewById(R.id.BuyActivityBackBtn)
         purchaseStockBackBtn.setOnClickListener {
             finish()
         }
        val stockId= intent.getIntExtra("STOCKID",1)
        BuyRegularActivity.PurchasedStockId = stockId
        adapter = FragmentPageAdapter(supportFragmentManager ,lifecycle)

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