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
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

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

        RandomIPOData()

    }
    fun RandomIPOData() {
        val IPONames = arrayOf("Juniper Hotels","GPT Healthcare","Platinum Industries","Vibhor Steel Tubes","Rashi Peripherals","Nova AgriTech", "BLS E-Services")
        val BSEorNSE = arrayOf("NSE","BSE","BSE","NSE","NSE","BSE","NSE")
        val MinPrice = arrayOf(340.00,177.00,150.00,280.00,414.00,370.00,41.00)
        val MaxPrice = arrayOf(362.00,182.00,159.00,292.00,424.00,380.00,47.00)
        val LotSize = arrayOf(99,80,45,36,70,96,66)
        val IPOLogos = arrayOf(
            R.drawable.juniper_hotels_logo_image,
            R.drawable.gpt_healthcare_logo,
            R.drawable.platinum_logo,
            R.drawable.vibhor_steel_tubes_logo,
            R.drawable.rashi_peripherals_logo,
            R.drawable.nova_agritech_ipo,
            R.drawable.bls_e_services_ipo_gmp)

        val startDate = arrayListOf<Date>()
        val endDate = arrayListOf<Date>()

        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        calendar.add(Calendar.DATE, -5)
        val fiveDaysBefore = calendar.time

        repeat(3) {
            val randomStartDate = generateRandomDate(fiveDaysBefore, currentDate)
            startDate.add(randomStartDate)
        }

        calendar.time = currentDate
        calendar.add(Calendar.DATE, 5)
        val fiveDaysAfter = calendar.time

        repeat(4) {
            val randomStartDate = generateRandomDate(currentDate, fiveDaysAfter)
            startDate.add(randomStartDate)
        }

        startDate.forEach { start ->
            val randomEndDate = generateRandomEndDate(start)
            endDate.add(randomEndDate)
        }

        startDate.forEachIndexed { index, date ->
            val message = "Entry ${index + 1}: Start Date: $date, End Date: ${endDate[index]}"
            Log.d("DateGeneration", message) // Log the message
        }

    }
    fun generateRandomDate(startDate: Date, endDate: Date): Date {
        val startMillis = startDate.time
        val endMillis = endDate.time
        val randomMillisSinceEpoch = Random.nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }

    fun generateRandomEndDate(startDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = startDate
        calendar.add(Calendar.DATE, Random.nextInt(4, 9))
        return calendar.time
    }
}