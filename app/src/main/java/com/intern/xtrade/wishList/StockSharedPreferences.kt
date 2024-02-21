package com.intern.xtrade.wishList

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import java.sql.Time
import kotlin.random.Random
object StockSharedPreferences {
    private const val PREF_NAME = "stock_pref"
    private const val KEY_STOCK_DATA = "stock_data"
    // Save sample stock data to SharedPreferences
    fun saveSampleStockData(context: Context) {
        val sampleStockData = getSampleStockData()
        val gson = Gson()
        val json = gson.toJson(sampleStockData)
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_STOCK_DATA, json).apply()
    }
    // Get sample stock data from SharedPreferences
    fun getSampleStockData(context: Context): List<StockInfo> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_STOCK_DATA, null)
        val gson = Gson()
        val type = object : TypeToken<List<StockInfo>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
    // Generate sample stock data
    private fun getSampleStockData(): List<StockInfo> {
        val sampleStockData = mutableListOf<StockInfo>()
        val companyLogo = listOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15
        )
        val companyNames = listOf(
            "ABC Corporation",
            "XYZ Inc.",
            "Tech Innovators",
            "Global Traders",
            "Green Energy Co.",
            "Silver Enterprises",
            "Star Solutions",
            "Alpha Investments",
            "Infinite Dynamics",
            "Future Tech",
            "Sunrise Holdings",
            "Quantum Systems",
            "Oceanic Ventures",
            "Pinnacle Innovations",
            "Blue Sky Investments"
        )
        val stockNames = listOf(
            "TechStock",
            "GlobalTraders",
            "GreenEnergy",
            "SilverStock",
            "StarSolutions",
            "AlphaInvest",
            "InfiniteDynamics",
            "FutureTech",
            "SunriseHoldings",
            "QuantumSystems",
            "OceanVentures",
            "PinnacleInnovations",
            "BlueSkyStock",
            "EcoFutures",
            "DynamicVisions"
        )
        val random = Random.Default
        for (i in 1..14) {
            val stock = StockInfo(
                CompanyName = companyNames[i - 1],
                CompanyLogo = companyLogo[i - 1], // Replace with actual drawable resource
                StockName = stockNames[i - 1],
                GraphBoolean = random.nextBoolean(),
                StockPrice = i * 1234 + i * 10 + Random.nextDouble(0.01),
                StockPercentage = 5.0 + i * 0.5,
                StockId = i,
                isInWatchList = false,
                isInHoldings = false,
                isInOrders = false,
            )
            sampleStockData.add(stock)
        }
        return sampleStockData
    }
}