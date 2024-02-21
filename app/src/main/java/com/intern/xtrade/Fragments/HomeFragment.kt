package com.intern.xtrade.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import com.intern.xtrade.Repositories.StockRepository
import com.intern.xtrade.SearchBarActivity
import com.intern.xtrade.StockScreen
import com.intern.xtrade.wishList.StockSharedPreferences
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var stocksToAdd :List<StockInfo> = listOf()

    private lateinit var searchIcon : ImageView
    private lateinit var TrendingLinearLayout : LinearLayout
    lateinit var investToday: CardView
    lateinit var stockRepository: StockRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Homepage_launched")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        searchIcon = view.findViewById(R.id.SearchIconId)
        investToday = view.findViewById(R.id.home_rectangle)
        investToday.findViewById<AppCompatButton>(R.id.InvestTodayId).setOnClickListener {
            val intent = Intent(requireContext(),SearchBarActivity::class.java)
            startActivity(intent)
        }
        searchIcon.setOnClickListener {
            val intent = Intent(requireContext(),SearchBarActivity::class.java)
            startActivity(intent)
        }
        TrendingLinearLayout = view.findViewById<LinearLayout>(R.id.home_cardsContainer)
        stockRepository = StockRepository(StockDataBase.invoke(requireContext()))

         val list = WishlistManager.StocksToAdd.shuffle()
        CreateListAndAppendToLayout(WishlistManager.StocksToAdd)
        return view
    }

    private fun CreateListAndAppendToLayout(totalStockList: List<StockInfo>) {
        lifecycleScope.launch(Dispatchers.IO) {
            for (stock in totalStockList) {
                val cardView = layoutInflater.inflate(R.layout.stock_card, null)
                cardView.findViewById<TextView>(R.id.card_stock_name).text = "${stock.StockName.substring(0, 7)}.."
                cardView.findViewById<TextView>(R.id.card_stock_inr).text = "₹ ${stock.StockPrice}".substring(0, 7) + ".."
                cardView.findViewById<TextView>(R.id.card_stock_company).text = " ${stock.CompanyName}"
                cardView.findViewById<ImageView>(R.id.card_stock_logo).setImageResource(stock.CompanyLogo)
                val stockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
                if (stock.GraphBoolean) {
                    val v1 = listOf<Int>(R.drawable.up_graph, R.drawable.upgraph_2)
                    cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(v1.random())
                    stockPercent.text = "+ ${stock.StockPercentage}"
                    stockPercent.setTextColor(resources.getColor(R.color.green))
                } else {
                    val v2 = listOf<Int>(R.drawable.downgraph_1, R.drawable.downgraph_2)
                    cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(v2.random())
                    stockPercent.text = "- ${stock.StockPercentage}"
                    stockPercent.setTextColor(resources.getColor(R.color.red))
                }
                cardView.setOnClickListener {
                    navigationationToStockScreen(stock)
                }
                withContext(Dispatchers.Main) {
                    // Set margin for card view
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.margin_between_cards), 0, resources.getDimensionPixelSize(R.dimen.margin_between_cards), resources.getDimensionPixelSize(R.dimen.margin_between_cards))
                    cardView.layoutParams = layoutParams
                    // Add card view to linear layout
                    TrendingLinearLayout.addView(cardView)
                }
            }
        }
    }

    private fun navigationationToStockScreen(Stock:StockInfo) {
        val intent = Intent(requireContext(),StockScreen::class.java)
        Log.i("KOUSIKHOMEFRAG","${Stock}")
        intent.putExtra("STOCKNAME",Stock.StockName)
        intent.putExtra("COMPANYLOGO",Stock.CompanyLogo)
        intent.putExtra("STOCKPRICE",Stock.StockPrice)
        intent.putExtra("STOCKPERCENTAGE",Stock.StockPercentage)
        intent.putExtra("GRAPHBOOLEAN",Stock.GraphBoolean)
        intent.putExtra("STOCKID",Stock.StockId)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}