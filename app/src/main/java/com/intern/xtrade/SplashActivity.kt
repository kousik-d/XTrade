package com.intern.xtrade

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.DataBases.IPODataBase
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Repositories.IPORepository
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import kotlin.random.Random


class SplashActivity:  AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferences1: SharedPreferences
    lateinit var sharedPrefereneForUserAttributes: SharedPreferences
    lateinit var sharedPreferencesMoney: SharedPreferences
    lateinit var stockRepository: StockRepository
    lateinit var ipoRepository: IPORepository
    lateinit var XtradeImageView: ImageView
    var totalMoney = 0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_splash_screen)

        XtradeImageView = findViewById(R.id.imageView)

        sharedPreferences = getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences1 = getSharedPreferences("MONEY", Context.MODE_PRIVATE)
        sharedPreferencesMoney = getSharedPreferences("MONEY", Context.MODE_PRIVATE)

        var investedValue = sharedPreferences1.getFloat("INVESTEDVALUE", 0.0f)
        sharedPrefereneForUserAttributes = getSharedPreferences("USERATTR", Context.MODE_PRIVATE)








        stockRepository = StockRepository(StockDataBase.invoke(this))
        ipoRepository = IPORepository(IPODataBase.invoke(this))

        val curr = sharedPreferences.getInt("DBSTEP", -1)

        if (curr == -1) {
            lifecycleScope.launch(Dispatchers.IO) {
                Log.i("ONETIMETAKE", "EXECUTINGONE")
                insertIntoDbStocks(getSampleStockData())
            }
            sharedPreferences1.edit().putFloat("HOLDINGVALUE", 37320.02f).apply()
            sharedPreferences1.edit().putFloat("INVESTEDVALUE", 37320.02f).apply()
            sharedPreferences1.edit().putInt("AVAILABLEINR", 0).apply()
        }
        WishlistManager.IPoData = RandomIPOData()
        stockRepository.allStocks.observe(this, Observer {
            val stocks = mutableListOf<StockInfo>()
            for (stock in it) {
                if (stock.isInHoldings != true) {
                    stocks.add(stock)
                }
            }
            WishlistManager.StocksToAdd = stocks
        })
        Log.i("IPODATAINSPLASH", "${RandomIPOData()}")
        ipoRepository.allIPOs.asLiveData().observe(this) {
            Log.i("IPOFROMDB", "${it}")
        }
        sharedPreferences.edit().putInt("DBSTEP", 100).apply()

    }


    suspend fun insertIntoDbIPO(IPOS: List<IPOData>) {
        for (i in IPOS) {
            ipoRepository.insertIPO(i)
        }
    }

    suspend fun insertIntoDbStocks(stocks: List<StockInfo>) {
        for (i in stocks) {
            stockRepository.insertStock(i)
        }
    }


    fun getSampleStockData(): List<StockInfo> {
        val sampleStockData = mutableListOf<StockInfo>()


        var companyNames = listOf(
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

        val companyLogo = listOf<Int>(
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
        var totalPrice = 0.0f
        for (i in 1..15) {
            val companyNam = "R.Drawable.img${i}"
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
                isInOrders = 0,
            )
            totalPrice += stock.StockPrice.toFloat()
            if (i % 5 == 0) {
                Log.i("STOCKS", "${stock}")
                stock.isInHoldings = true
            }
            sampleStockData.add(stock)
        }
        return sampleStockData
    }

    fun RandomIPOData(): MutableList<IPOData> {
        val IPODataList = mutableListOf<IPOData>()
        val IPONames = arrayOf(
            "Juniper Hotels",
            "GPT Healthcare",
            "Platinum Industries",
            "Vibhor Steel Tubes",
            "Rashi Peripherals",
            "Nova AgriTech",
            "BLS E-Services"
        )
        val BSEorNSE = arrayOf("NSE", "BSE", "BSE", "NSE", "NSE", "BSE", "NSE")
        val MinPrice = arrayOf(340.00, 177.00, 150.00, 280.00, 414.00, 370.00, 41.00)
        val MaxPrice = arrayOf(362.00, 182.00, 159.00, 292.00, 424.00, 380.00, 47.00)
        val LotSize = arrayOf(99, 80, 45, 36, 70, 96, 66)
        val IPOLogos = arrayOf(
            R.drawable.juniper_hotels_logo_image,
            R.drawable.gpt_healthcare_logo,
            R.drawable.platinum_logo,
            R.drawable.vibhor_steel_tubes_logo,
            R.drawable.rashi_peripherals_logo,
            R.drawable.nova_agritech_ipo,
            R.drawable.bls_e_services_ipo_gmp
        )

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

        for (i in 0..6) {
            val ipodata = IPOData(
                IPOId = i,
                IPOName = IPONames[i],
                IPOImage = IPOLogos[i],
                IPOOpenDate = startDate[i].toString(),
                IPOCloseDate = endDate[i].toString(),
                IPOMinPrice = MinPrice[i].toString(),
                IPOMaxPrice = MaxPrice[i].toString(),
                IPOLotsize = LotSize[i],
                isIPOOpen = false,
                isApplied = false,
                isForthComing = false,
                MinInvestments = Random.nextInt(9000, 15000)
            )
            IPODataList.add(ipodata)
        }
        return IPODataList
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
        calendar.add(Calendar.DATE, Random.nextInt(6, 9))
        return calendar.time
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            val LoadAct = sharedPreferences.getInt("current_step", -1)
            Log.i("SPLASHLOADACT", "${LoadAct}")
            if (LoadAct == 0) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else if (arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).contains(LoadAct)) {
                val intent = Intent(this, UserDetails::class.java)
                intent.putExtra("FRAGMENTTOLOAD", LoadAct)
                startActivity(intent)
            } else {
                val intent = Intent(this, AppOpenActivity::class.java)
                startActivity(intent)
            }
        }, 2000)
    }
}