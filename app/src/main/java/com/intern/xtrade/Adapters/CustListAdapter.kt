package com.intern.xtrade.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import kotlin.random.Random

class CustListAdapter(val context: Activity ,val listOfStocks : List<StockInfo>): ArrayAdapter<StockInfo>(
    context, R.layout.stock_card,listOfStocks
){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.stock_card,null)

        view.findViewById<TextView>(R.id.card_stock_name).text = listOfStocks[position].StockName.substring(0,7)+".."
        view.findViewById<TextView>(R.id.card_stock_inr).text = "₹ ${listOfStocks[position].StockPrice}".substring(0,7)+".."
        view.findViewById<TextView>(R.id.card_stock_company).text = " ${listOfStocks[position].CompanyName}"
        view.findViewById<ImageView>(R.id.card_stock_logo).setImageResource(listOfStocks[position].CompanyLogo)
        if(listOfStocks[position].GraphBoolean == true){
            val v1 = listOf<Int>(R.drawable.up_graph,R.drawable.upgraph_2)
            view.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
                v1[Random.nextInt(2)]
            )
            var StockPercent = view.findViewById<TextView>(R.id.card_stock_percentage)
            StockPercent.text = "+ ${listOfStocks[position].StockPercentage}"
            StockPercent.setTextColor(ContextCompat.getColor(getContext(),R.color.green))
        }else{
            val v2 = listOf<Int>(R.drawable.downgraph_1,R.drawable.downgraph_2)
            view.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
                v2[Random.nextInt(2)]
            )
            var StockPercent = view.findViewById<TextView>(R.id.card_stock_percentage)
            StockPercent.text = "- ${listOfStocks[position].StockPercentage}"
            StockPercent.setTextColor(ContextCompat.getColor(getContext(),R.color.red))
        }
        return view
    }
}