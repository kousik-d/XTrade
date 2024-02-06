package com.intern.xtrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import com.intern.xtrade.Adapters.CustListAdapter
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Fragments.HomeFragment

class SearchBarActivity : AppCompatActivity() {
    lateinit var cancelText : TextView
    lateinit var yourWishList : TextView
    lateinit var ListtoShow : ListView
    lateinit var SearchView : androidx.appcompat.widget.SearchView

    private var totalStocks = HomeFragment().getSampleStockData()
    private var searchStock = arrayListOf<StockInfo>()
    private var namesOfCompany = HomeFragment().companyNames



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)

        cancelText = findViewById(R.id.CancelTextId)
        yourWishList = findViewById(R.id.wishListId)
        ListtoShow = findViewById(R.id.ListOfStocksId)
        SearchView = findViewById(R.id.SearchViewToStock)

        createListViewAdapter(totalStocks)
        cancelText.setOnClickListener {
            finish()
        }


        SearchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                SearchView.clearFocus()
                val indexToshow = searchInUserAdapter(query)
                if(indexToshow<=15){
                    val filteredList = totalStocks.filterIndexed{ index, stockInfo ->
                        index == indexToshow
                    }
                    createListViewAdapter(filteredList)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        yourWishList.setOnClickListener {
            val intent = Intent(this,YourWishlist::class.java)
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