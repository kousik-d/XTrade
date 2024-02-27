package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.intern.xtrade.Adapters.CustListAdapter
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Fragments.HomeFragment
import com.intern.xtrade.ProfileActivites.YourWishlist
import com.intern.xtrade.wishList.WishlistManager
import java.util.Locale

class SearchBarActivity : AppCompatActivity() {
    lateinit var cancelText : ImageView
    lateinit var ListtoShow : ListView
    lateinit var SearchView : androidx.appcompat.widget.SearchView

    private var totalStocks = WishlistManager.StocksToAdd
    private var searchStock = arrayListOf<StockInfo>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)

        cancelText = findViewById(R.id.SearchBarBackBtn)
        ListtoShow = findViewById(R.id.ListOfStocksId)
        SearchView = findViewById(R.id.SearchViewToStock)
        Log.i("SEARCHBAR","SEARCH")

        createListViewAdapter(totalStocks)
        cancelText.setOnClickListener {
            finish()
        }


        SearchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                SearchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchStock.clear()
                val searchText = newText?.toLowerCase(Locale.getDefault())
                searchText?.let {searchText->
                    if(searchText.isNotEmpty()){
                        totalStocks.forEach {
                            if(it.StockName.contains(searchText,ignoreCase = true)){
                                searchStock.add(it)
                            }
                        }
                        ListtoShow.adapter = CustListAdapter(this@SearchBarActivity,searchStock)
                        ListtoShow.isClickable = true
                        ListtoShow.setOnItemClickListener { parent, view, position, id ->
                            val intent = Intent(this@SearchBarActivity,StockScreen::class.java)
                            Log.i("KOUSIKHOMEFRAG","${searchStock[position]}")
                            intent.putExtra("COMPANYNAME",searchStock[position].CompanyName)
                            intent.putExtra("STOCKNAME",searchStock[position].StockName)
                            intent.putExtra("COMPANYLOGO",searchStock[position].CompanyLogo)
                            intent.putExtra("STOCKPRICE",searchStock[position].StockPrice)
                            intent.putExtra("STOCKPERCENTAGE",searchStock[position].StockPercentage)
                            intent.putExtra("GRAPHBOOLEAN",searchStock[position].GraphBoolean)
                            intent.putExtra("STOCKID",searchStock[position].StockId)
                            startActivity(intent)
                        }
                    }else{
                        searchStock.clear()
                        ListtoShow.adapter = CustListAdapter(this@SearchBarActivity,totalStocks)
                    }
                }
                return false
            }

        })

    }

    private fun createListViewAdapter(InputStocks: List<StockInfo>) {
        val userAdapter = CustListAdapter(this, InputStocks)
        ListtoShow.adapter = userAdapter

        ListtoShow.isClickable = true
        ListtoShow.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, StockScreen::class.java)
            intent.putExtra("STOCKNAME", InputStocks[position].StockName)
            intent.putExtra("COMPANYLOGO", InputStocks[position].CompanyLogo)
            intent.putExtra("STOCKPRICE", InputStocks[position].StockPrice)
            intent.putExtra("STOCKPERCENTAGE", InputStocks[position].StockPercentage)
            intent.putExtra("GRAPHBOOLEAN", InputStocks[position].GraphBoolean)
            intent.putExtra("STOCKID", InputStocks[position].StockId)
            startActivity(intent)
        }
    }

    private fun searchInUserAdapter(StockName : String?): Int {
        StockName?.let {
            for(i in 0..(totalStocks.size-1)){
                if(totalStocks[i].CompanyName.contains(StockName,ignoreCase = true)){
                        return i;
                }
            }
        }
        return 100;
    }
}