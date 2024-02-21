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
import androidx.lifecycle.lifecycleScope
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class SplashActivity:  AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var stockRepository : StockRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        setContentView(R.layout.actvity_splash_screen)
        stockRepository = StockRepository(StockDataBase.invoke(this))

        val curr = sharedPreferences.getInt("DBSTEP",-1)

        if(curr == -1){
            lifecycleScope.launch(Dispatchers.IO) {
                Log.i("ONETIMETAKE","EXECUTINGONE")
                insertIntoDb(getSampleStockData())
            }
        }

        stockRepository.allStocks.observe(this){
            WishlistManager.StocksToAdd = it
        }
        sharedPreferences.edit().putInt("DBSTEP",100).apply()

    }

    private fun doNothing() {

    }
    suspend fun insertIntoDb(stocks:List<StockInfo> ){
        for (i in stocks){
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

        for (i in 1..14) {
            val companyNam = "R.Drawable.img${i}"
            val stock = StockInfo(
                CompanyName = companyNames[i-1],
                CompanyLogo = companyLogo[i-1], // Replace with actual drawable resource
                StockName = stockNames[i-1],
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

    override fun onResume() {
        super.onResume()

        Handler().postDelayed({
            val LoadAct = sharedPreferences.getInt("current_step",-1)
            Log.i("SPLASHLOADACT","${LoadAct}")
            if(LoadAct == 0){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else if(arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10,11).contains(LoadAct)){
                val intent = Intent(this,UserDetails::class.java)
                intent.putExtra("FRAGMENTTOLOAD",LoadAct)
                startActivity(intent)
            }
            else{
                val intent = Intent(this,AppOpenActivity::class.java)
                startActivity(intent)
            }},2000)
    }
}