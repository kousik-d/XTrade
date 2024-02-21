package com.intern.xtrade.IPO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.apxor.androidsdk.core.ApxorSDK
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.intern.xtrade.Adapters.FragmentPageAdapter
import com.intern.xtrade.Adapters.FragmentPageAdapterIPO
import com.intern.xtrade.R

class IPOActivity : AppCompatActivity() {
    lateinit var IPOviewPager: ViewPager2
    lateinit var IPOtabLayout: TabLayout
    lateinit var adapter : FragmentPageAdapterIPO
    lateinit var IPOActivityBackbtn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipoactivity)

        IPOviewPager = findViewById(R.id.ipo_viewPager)
        IPOtabLayout = findViewById(R.id.ipo_tabLayout)
        IPOActivityBackbtn = findViewById(R.id.IPOActivityBack)

        IPOActivityBackbtn.setOnClickListener {
            finish()
        }
        adapter = FragmentPageAdapterIPO(supportFragmentManager , lifecycle)
        IPOviewPager.adapter = adapter

        IPOtabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    Log.i("TABCHECK","${it.position}")
                    IPOviewPager.currentItem = it.position;
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        IPOviewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                IPOtabLayout.selectTab(IPOtabLayout.getTabAt(position))
            }
        })

    }
}