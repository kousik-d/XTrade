package com.intern.xtrade.Orders

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Index.Order
import androidx.viewpager2.widget.ViewPager2
import com.apxor.androidsdk.core.ApxorSDK
import com.google.android.material.tabs.TabLayout
import com.intern.xtrade.Adapters.FragmentPageAdapter
import com.intern.xtrade.Adapters.FragmentPageAdapterOrders
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R
import java.util.jar.Attributes

class YourOrders : AppCompatActivity() {

    lateinit var yourOrdersBack : ImageView
    lateinit var OrderViewPager : ViewPager2
    lateinit var adapter : FragmentPageAdapterOrders
    lateinit var tabLayout : TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_orders)

        val source = intent.getStringExtra("Source")
        var order = "Open"

        yourOrdersBack = findViewById(R.id.OrdersBackBtn)
        OrderViewPager = findViewById(R.id.orders_viewPager)
        tabLayout = findViewById(R.id.order_tabLayout)

        adapter = FragmentPageAdapterOrders(supportFragmentManager,lifecycle)
        OrderViewPager.adapter = adapter

        yourOrdersBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    Log.i("TABCHECK","${it.position}")
                    if(it.position == 0)
                        order = "Open"
                    else
                        order = "Executed"
                    OrderViewPager.currentItem = it.position
                    val attrs = com.apxor.androidsdk.core.Attributes()
                    attrs.putAttribute("Source",source)
                    attrs.putAttribute("Order",order)
                    ApxorSDK.logAppEvent("Order_page_launched",attrs)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        OrderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}