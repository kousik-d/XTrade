package com.intern.xtrade

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.Daos.StockDao
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.RegularAndAMO.BuyRegularActivity
import com.intern.xtrade.RegularAndAMO.Buy_activity
import com.intern.xtrade.RegularAndAMO.SellActivity
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.Calendar


class StockScreen : AppCompatActivity() {

    lateinit var stockLogoImage : ImageView
    lateinit var stockName : TextView
    lateinit var stockValue : TextView
    lateinit var stockScreenPercentage : TextView
    lateinit var stockScreenBuyButton : Button
    lateinit var stockScreenSellButton : Button
    lateinit var stockScreenBack : ImageView
    lateinit var AddToWishList : CheckBox
    lateinit var StockBuyButton : Button
    lateinit var graph : ImageView
    lateinit var stockRepository: StockRepository
    lateinit var totalStocks : MutableList<StockInfo>

    private val buttons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_screen)

        stockRepository = StockRepository(StockDataBase.invoke(this))


        val companyName = intent.getStringExtra("STOCKNAME")
        val companyLogo= intent.getIntExtra("COMPANYLOGO",R.drawable.apxor_x_logo)
        val stockPrice = intent.getDoubleExtra("STOCKPRICE",0.00)
        val stockPercentage = intent.getDoubleExtra("STOCKPERCENTAGE",0.00)
        val stockBoolean = intent.getBooleanExtra("GRAPHBOOLEAN",false)
        val stockId = intent.getIntExtra("STOCKID",0)

        val attrs = Attributes();
        attrs.putAttribute("Stock Name",companyName.toString())
        ApxorSDK.logAppEvent("Stocks_clicked",attrs)

        stockRepository.allStocks.observe(this){
            totalStocks = it
            updateWishListInStockScreen(it,stockId)
        }

        graph = findViewById(R.id.graph_view)

        val graphIds = arrayOf(
            R.drawable.graph_2,
            R.drawable.graph_3,
            R.drawable.graph_1,
            R.drawable.graph_4,
            R.drawable.graph_5,
            R.drawable.stock_graph
        )

        var graphID = arrayListOf<Int>()

        if(stockBoolean) {
            graph.setImageResource(R.drawable.stock_graph)
            graphID.add(R.drawable.stock_graph)
        }
        else{
            graph.setImageResource(R.drawable.graph_5)
            graphID.add(R.drawable.graph_5)
        }

        for (i in 1..6)
        {
            val randomIndex = (0 until graphIds.size).random()
            graphID.add(graphIds[randomIndex])
        }


        stockName = findViewById(R.id.stock_name)
        stockLogoImage = findViewById(R.id.stock_logo)
        stockValue = findViewById(R.id.stock_screen_value)
        stockScreenPercentage = findViewById(R.id.stock_screen_percentage)
        stockScreenBuyButton = findViewById(R.id.Stock_Screen_BuyButton)
        stockScreenSellButton = findViewById(R.id.Stock_Screen_SellButton)
        stockScreenBack = findViewById(R.id.BackButtonImage)
        AddToWishList = findViewById(R.id.AddToWishListCheckBox)
        StockBuyButton = findViewById(R.id.Stock_Screen_BuyButton)

        stockScreenSellButton.setOnClickListener {
            if(checkStockInHoldingsStock(stockId)){
                val intent = Intent(this,SellActivity::class.java)
                intent.putExtra("SELLSTOCKID",stockId)
                startActivity(intent)
            }else{
                Toast.makeText(this,"You cannot sell this stock",Toast.LENGTH_SHORT).show()
            }
        }

        initButtons(graphID)

        StockBuyButton.setOnClickListener {
            val intent = Intent(this, Buy_activity::class.java)
            intent.putExtra("STOCKID",stockId)
            startActivity(intent)
        }
        stockScreenBack.setOnClickListener {
            finish()
        }
        if(stockBoolean){
            stockScreenPercentage.text = "+ ${stockPercentage}"
            stockScreenPercentage.setTextColor(getColor(R.color.green))
        }else{
            stockScreenPercentage.text = "- ${stockPercentage}"
            stockScreenPercentage.setTextColor(getColor(R.color.red))
        }
        val formatedval = String.format("%.2f",stockPrice)
        stockValue.text = "₹ ${formatedval}"

        stockName.text = companyName
        if(companyLogo!=null) {
            stockLogoImage.setImageResource(companyLogo)
        }


        AddToWishList.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                companyName?.let {
                    val index = totalStocks.indexOfFirst{ it.StockId == stockId }

                    if(index!=-1) {
                        val newStock = totalStocks[index]
                        if (newStock.isInWatchList != true) {
                            newStock.isInWatchList = true
                            lifecycleScope.launch(IO) {
                                updateStock(newStock)
                            }
                            Toast.makeText(this,"Added to watchlist",Toast.LENGTH_SHORT).show()

                            val attrs1 = Attributes();
                            attrs.putAttribute("Stock Name",companyName.toString())
                            ApxorSDK.logAppEvent("Add _to_watchlist",attrs1)
                        }
                    }
                }
            }else{
                val index = totalStocks.indexOfFirst{ it.StockId == stockId }
                if(index!=-1){
                    val newStock = totalStocks[index]
                    newStock.isInWatchList = false
                    lifecycleScope.launch(IO) {
                        updateStock(newStock)
                    }
                }
                Toast.makeText(this,"Removed from watchlist",Toast.LENGTH_SHORT).show()
                val attrs2 = Attributes();
                attrs.putAttribute("Stock Name",companyName.toString())
                ApxorSDK.logAppEvent("Remove _from_watchlist",attrs2)

            }
        }
    }

    private fun checkStockInHoldingsStock(stockId : Int): Boolean {
        val holdings = WishlistManager.getYourStocks(this)
        for(i in holdings){
            if(i==stockId){
                return true
            }
        }
        return false
    }



    private fun initButtons(graphID : ArrayList<Int>) {
        val buttonIds = arrayOf(
            R.id.stock_button_1H,
            R.id.stock_button_24H,
            R.id.stock_button_1W,
            R.id.stock_button_1M,
            R.id.stock_button_6M,
            R.id.stock_button_1Y,
            R.id.stock_button_ALL,
        )

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        val textArray = arrayOf(
            R.id.text1,
            R.id.text2,
            R.id.text3,
            R.id.text4,
            R.id.text5,
            )

        for (i in 0..6) {
            val button = findViewById<Button>(buttonIds[i])
            button.setOnClickListener {
                graph.setImageResource(graphID[i])
                if(i==0)
                {
                    if(hour==0)
                        hour=23
                    var min = 0
                    for(i in textArray)
                    {
                        min +=12
                        val text = findViewById<TextView>(i)
                        text.text = (hour-1).toString() + ":" + min.toString()
                    }
                }
                onButtonClick(button)
            }
            buttons.add(button)
        }
    }
    private fun onButtonClick(clickedButton: Button) {


        val drawable1: Drawable = this.getDrawable(R.drawable.stock_screen_button_clicked)!!
        val drawable2: Drawable = this.getDrawable(R.drawable.stock_screen_button)!!

        clickedButton.background = drawable1
        clickedButton.setTextColor(ContextCompat.getColor(this,R.color.card_blue))

        for (button in buttons) {
            if (button.id != clickedButton.id) {
                button.background = drawable2
                button.setTextColor(ContextCompat.getColor(this,R.color.darkBlack))
            }
        }
    }
    suspend fun updateStock(stockInfo: StockInfo){
        stockRepository.updateStock(stockInfo)
    }

    fun updateWishListInStockScreen(stockList : MutableList<StockInfo>, stockIdToCheck : Int){
        val index = stockList.indexOfFirst{ it.StockId == stockIdToCheck }
        if(index != -1){
            if(totalStocks[index].isInWatchList == true)
                AddToWishList.isChecked = true
            else
                AddToWishList.isChecked = false
        }
    }
}