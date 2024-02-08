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
import java.util.Locale

class SearchBarActivity : AppCompatActivity() {
    lateinit var cancelText : ImageView
    lateinit var yourWishList : TextView
    lateinit var ListtoShow : ListView
    lateinit var SearchView : androidx.appcompat.widget.SearchView

    private var totalStocks = HomeFragment().getSampleStockData()
    private var searchStock = arrayListOf<StockInfo>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)

        cancelText = findViewById(R.id.SearchBarBackBtn)
        yourWishList = findViewById(R.id.wishListId)
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
                            if(it.CompanyName.contains(searchText,ignoreCase = true)){
                                searchStock.add(it)
                            }
                        }
                        ListtoShow.adapter = CustListAdapter(this@SearchBarActivity,searchStock)
                        ListtoShow.isClickable = true
                        ListtoShow.setOnItemClickListener { parent, view, position, id ->
                            val intent = Intent(this@SearchBarActivity,StockScreen::class.java)
                            Log.i("KOUSIKHOMEFRAG","${searchStock[position]}")
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
        yourWishList.setOnClickListener {
            val intent = Intent(this, YourWishlist::class.java)
            startActivity(intent)
        }
    }

    private fun createListViewAdapter(InputStocks:List<StockInfo>) {
        val userAdapter :ArrayAdapter<StockInfo> = ArrayAdapter(
            this,R.layout.stock_card,InputStocks
        )
        ListtoShow.adapter = CustListAdapter(this,InputStocks)

        ListtoShow.isClickable = true
        ListtoShow.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,StockScreen::class.java)
            Log.i("KOUSIKHOMEFRAG","${InputStocks[position]}")
            intent.putExtra("STOCKNAME",InputStocks[position].StockName)
            intent.putExtra("COMPANYLOGO",InputStocks[position].CompanyLogo)
            intent.putExtra("STOCKPRICE",InputStocks[position].StockPrice)
            intent.putExtra("STOCKPERCENTAGE",InputStocks[position].StockPercentage)
            intent.putExtra("GRAPHBOOLEAN",InputStocks[position].GraphBoolean)
            intent.putExtra("STOCKID",InputStocks[position].StockId)
            startActivity(intent)
        }
    }
    private fun searchInUserAdapter(StockName : String?): Int {
        StockName?.let {
            for(i in 1..13){
                if(totalStocks[i].CompanyName.contains(StockName,ignoreCase = true)){
                        return i;
                }
            }
        }
        return 100;
    }
}