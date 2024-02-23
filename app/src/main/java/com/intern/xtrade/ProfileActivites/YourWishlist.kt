package com.intern.xtrade.ProfileActivites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import com.intern.xtrade.Adapters.CustListAdapter
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.MainActivity
import com.intern.xtrade.R
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.StockScreen
import com.intern.xtrade.wishList.WishlistManager


class YourWishlist : AppCompatActivity() {

    lateinit var YourWishListBack : ImageView
    lateinit var WishlistListView : ListView
    lateinit var AllStocksInWishList : MutableList<Int>
    var totalStocks : MutableList<StockInfo> = mutableListOf()
    var stocksToDisplay = mutableListOf<StockInfo>()

    lateinit var stockRepository: StockRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_your_wishlist)
        AllStocksInWishList = WishlistManager.getWishlist(this)
        YourWishListBack = findViewById(R.id.YourWishlistBackId)
        WishlistListView = findViewById(R.id.totalwishList)
        stockRepository = StockRepository(StockDataBase.invoke(this))

        YourWishListBack.setOnClickListener {
            finish()
        }

        stockRepository.allStocks.distinctUntilChanged().observe(this){
            stocksToDisplay = CheckWishListDataInTotalData(it)
            val adapter = CustListAdapter(this,stocksToDisplay)
            adapter.notifyDataSetChanged()
            WishlistListView.adapter = adapter
        }


        WishlistListView.isClickable = true

        WishlistListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, StockScreen::class.java)
            intent.putExtra("STOCKNAME",stocksToDisplay[position].StockName)
            intent.putExtra("COMPANYLOGO",stocksToDisplay[position].CompanyLogo)
            intent.putExtra("STOCKPRICE",stocksToDisplay[position].StockPrice)
            intent.putExtra("STOCKPERCENTAGE",stocksToDisplay[position].StockPercentage)
            intent.putExtra("GRAPHBOOLEAN",stocksToDisplay[position].GraphBoolean)
            intent.putExtra("STOCKID",stocksToDisplay[position].StockId)
            startActivity(intent)
        }

    }
    fun CheckWishListDataInTotalData(infunctotalStocks : MutableList<StockInfo>) : MutableList<StockInfo>{
        var stocksToDisplay = mutableListOf<StockInfo>()
        for(stock in infunctotalStocks){
            if(stock.isInWatchList == true){
                stocksToDisplay.add(stock)
            }else{
                stocksToDisplay.remove(stock)
            }
        }
        return stocksToDisplay
    }
}