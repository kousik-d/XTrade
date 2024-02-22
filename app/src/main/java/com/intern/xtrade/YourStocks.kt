package com.intern.xtrade

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.Adapters.CustListAdapter
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.ProfileActivites.YourWishlist
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.wishList.WishlistManager


class YourStocks : AppCompatActivity() {

    lateinit var YourStockBackBtn : ImageView
    lateinit var StocksWishListBtn : AppCompatButton
    lateinit var YourStocksListView : ListView
    lateinit var stockRepository: StockRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_your_stocks)

        stockRepository = StockRepository(StockDataBase.invoke(this))

        StocksWishListBtn = findViewById(R.id.YourStocksWishListBtn)

        StocksWishListBtn.setOnClickListener {
            val intent = Intent(this,YourWishlist::class.java)
            startActivity(intent)
        }

        YourStocksListView = findViewById(R.id.yourStocksListview)

        stockRepository.allStocks.observe(this){
            val holdings = mutableListOf<StockInfo>()
            for(i in it){
                if(i.isInHoldings){
                    holdings.add(i)
                }
                val adapter = CustListAdapter(this,holdings)
                YourStocksListView.adapter = adapter
            }

        }

        YourStockBackBtn = findViewById(R.id.YourStocksBackBtn)

        YourStockBackBtn.setOnClickListener { finish() }
    }
}