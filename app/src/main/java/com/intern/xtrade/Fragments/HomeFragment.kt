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
import androidx.lifecycle.lifecycleScope
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import com.intern.xtrade.SearchBarActivity
import com.intern.xtrade.StockScreen
import com.intern.xtrade.wishList.StockSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private lateinit var searchIcon : ImageView
    private lateinit var TrendingLinearLayout : LinearLayout
    public var totalStockList : List<StockInfo> = getSampleStockData()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        searchIcon = view.findViewById(R.id.SearchIconId)
        searchIcon.setOnClickListener {
            val intent = Intent(requireContext(),SearchBarActivity::class.java)
            startActivity(intent)
        }
        TrendingLinearLayout = view.findViewById<LinearLayout>(R.id.home_cardsContainer)
        CreateListAndAppendToLayout(totalStockList)

        //Log.i("KOUSIKFILE","${singleton.singleValueFileManager.readDataFromFile("WISH.txt")}")
        return view
    }

    private fun CreateListAndAppendToLayout(totalStockList: List<StockInfo>) {

        lifecycleScope.launch(Dispatchers.IO) {
            for (stock in totalStockList) {
                val cardView = layoutInflater.inflate(R.layout.stock_card, null)
                cardView.findViewById<TextView>(R.id.card_stock_name).text =
                    stock.StockName.substring(0, 7) + ".."
                cardView.findViewById<TextView>(R.id.card_stock_inr).text =
                    "₹ ${stock.StockPrice}".substring(0, 7) + ".."
                cardView.findViewById<TextView>(R.id.card_stock_company).text =
                    " ${stock.CompanyName}"
                cardView.findViewById<ImageView>(R.id.card_stock_logo)
                    .setImageResource(stock.CompanyLogo)
                if (stock.GraphBoolean == true) {
                    val v1 = listOf<Int>(R.drawable.up_graph, R.drawable.upgraph_2)
                    cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
                        v1[Random.nextInt(2)]
                    )
                    var StockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
                    StockPercent.text = "+ ${stock.StockPercentage}"
                    StockPercent.setTextColor(resources.getColor(R.color.green))
                } else {
                    val v2 = listOf<Int>(R.drawable.downgraph_1, R.drawable.downgraph_2)
                    cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
                        v2[Random.nextInt(2)]
                    )
                    var StockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
                    StockPercent.text = "- ${stock.StockPercentage}"
                    StockPercent.setTextColor(resources.getColor(R.color.red))
                }
                cardView.setOnClickListener {
                    navigationationToStockScreen(stock)
                }
                withContext(Dispatchers.Main) {
                    TrendingLinearLayout.addView(cardView);
                }
            }
        }
        // Without using the Threads
//        for(stock in totalStockList){
//            val cardView = layoutInflater.inflate(R.layout.stock_card,null)
//            cardView.findViewById<TextView>(R.id.card_stock_name).text = stock.StockName.substring(0,7)+".."
//            cardView.findViewById<TextView>(R.id.card_stock_inr).text = "₹ ${stock.StockPrice}".substring(0,7)+".."
//            cardView.findViewById<TextView>(R.id.card_stock_company).text = " ${stock.CompanyName}"
//            cardView.findViewById<ImageView>(R.id.card_stock_logo).setImageResource(stock.CompanyLogo)
//            if(stock.GraphBoolean == true){
//                val v1 = listOf<Int>(R.drawable.up_graph,R.drawable.upgraph_2)
//                cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
//                    v1[Random.nextInt(2)]
//                )
//                var StockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
//                StockPercent.text = "+ ${stock.StockPercentage}"
//                StockPercent.setTextColor( resources.getColor(R.color.green))
//            }else{
//                val v2 = listOf<Int>(R.drawable.downgraph_1,R.drawable.downgraph_2)
//                cardView.findViewById<ImageView>(R.id.card_growth_image).setImageResource(
//                    v2[Random.nextInt(2)]
//                )
//                var StockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
//                StockPercent.text = "- ${stock.StockPercentage}"
//                StockPercent.setTextColor( resources.getColor(R.color.red))
//
//            }
//
//            cardView.setOnClickListener {
//                navigationationToStockScreen(stock)
//            }
//            TrendingLinearLayout.addView(cardView);
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
                StockId = i
            )
            sampleStockData.add(stock)
        }
        return sampleStockData
    }
}