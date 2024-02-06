package com.intern.xtrade.ProfileActivites

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.intern.xtrade.Adapters.CustListAdapter
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.R
import com.intern.xtrade.wishList.WishlistManager


class YourWishlist : AppCompatActivity() {

    lateinit var YourWishListBack : ImageView
    lateinit var WishlistListView : ListView
    lateinit var AllStocksInWishList : MutableList<Int>
    private var totalStocks = HomeFragment().getSampleStockData()
    private var stocksToDisplay = mutableListOf<StockInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_your_wishlist)
        AllStocksInWishList = WishlistManager.getWishlist(this)
        YourWishListBack = findViewById(R.id.YourWishlistBackId)
        WishlistListView = findViewById(R.id.totalwishList)
//        WishlistListView.
        CheckWishListDataInTotalData()
        WishlistListView.adapter = CustListAdapter(this,stocksToDisplay)
        YourWishListBack.setOnClickListener {
            finish()
        }

    }


    fun CheckWishListDataInTotalData(){
        for(stock in totalStocks){
            if(AllStocksInWishList.contains(stock.StockId)){
                stocksToDisplay.add(stock)
            }
        }
    }
}