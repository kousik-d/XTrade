package com.intern.xtrade.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.FinalPayment
import com.intern.xtrade.R
import com.intern.xtrade.StockScreen
import com.intern.xtrade.wishList.WishlistManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PortfolioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PortfolioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var DepositINRbtn : Button
    lateinit var WithDrawINRbtn : Button
    lateinit var availabeINR : TextView
    lateinit var PortfolioCard : CardView
    lateinit var PortfolioCardContainer : LinearLayout
    public var totalStockList : List<StockInfo> = WishlistManager.StocksToAdd
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
        val view = inflater.inflate(R.layout.fragment_portfolio, container, false)
        DepositINRbtn = view.findViewById(R.id.DepositINRId)
        WithDrawINRbtn = view.findViewById(R.id.WithDrawINRId)
        availabeINR = view.findViewById<CardView>(R.id.portfolio_rectangle).findViewById(R.id.portfolio_available_inr)
        PortfolioCardContainer = view.findViewById(R.id.portfolio_cardsContainer)

        Log.i("ALLHOLDINGS","${WishlistManager.getYourStocks(requireContext())}")

        CreateListAndAppendToLayout(WishlistManager.getYourStocks(requireContext()))

        val moneyPresent = availabeINR.text.toString().toInt()
        val indiLocal = Locale("en", "in")
        val totalMoney =  (moneyPresent + PortfolioFragment.AmountToAddToDeposit)
        availabeINR.text = NumberFormat.getCurrencyInstance(indiLocal).format(totalMoney)
        DepositINRbtn.setOnClickListener {
            val intent :Intent = Intent(requireContext(),DepositINRActivity::class.java)
            startActivity(intent)
        }
        WithDrawINRbtn.setOnClickListener {
            val intent: Intent = Intent(requireContext(),FinalPayment::class.java)
            Log.i("KOUSIKDASARI",availabeINR.text.toString())
            intent.putExtra("AVAILABLEINR",availabeINR.text.toString().drop(1))
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PortfolioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PortfolioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        var AmountToAddToDeposit = 0
    }


    private fun CreateListAndAppendToLayout(givenStockIds: MutableList<Int>) {
        lifecycleScope.launch(Dispatchers.IO) {
            for (stock in totalStockList) {
                if (givenStockIds.contains(stock.StockId)) {
                    val cardView = layoutInflater.inflate(R.layout.stock_card, null)
                    cardView.findViewById<TextView>(R.id.card_stock_name).text =
                        "${stock.StockName.substring(0, 7)}.."
                    cardView.findViewById<TextView>(R.id.card_stock_inr).text =
                        "₹ ${stock.StockPrice}".substring(0, 7) + ".."
                    cardView.findViewById<TextView>(R.id.card_stock_company).text =
                        " ${stock.CompanyName}"
                    cardView.findViewById<ImageView>(R.id.card_stock_logo)
                        .setImageResource(stock.CompanyLogo)
                    val stockPercent = cardView.findViewById<TextView>(R.id.card_stock_percentage)
                    if (stock.GraphBoolean) {
                        val v1 = listOf<Int>(R.drawable.up_graph, R.drawable.upgraph_2)
                        cardView.findViewById<ImageView>(R.id.card_growth_image)
                            .setImageResource(v1.random())
                        stockPercent.text = "+ ${stock.StockPercentage}"
                        stockPercent.setTextColor(resources.getColor(R.color.green))
                    } else {
                        val v2 = listOf<Int>(R.drawable.downgraph_1, R.drawable.downgraph_2)
                        cardView.findViewById<ImageView>(R.id.card_growth_image)
                            .setImageResource(v2.random())
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
                        layoutParams.setMargins(
                            resources.getDimensionPixelSize(R.dimen.margin_between_cards)
                            ,
                            0,
                            resources.getDimensionPixelSize(R.dimen.margin_between_cards)
                            ,
                            resources.getDimensionPixelSize(R.dimen.margin_between_cards)
                        )
                        cardView.layoutParams = layoutParams
                        // Add card view to linear layout
                        PortfolioCardContainer.addView(cardView)
                    }
                }
            }
        }
    }
    private fun navigationationToStockScreen(Stock:StockInfo) {
        val intent = Intent(requireContext(), StockScreen::class.java)
        Log.i("KOUSIKHOMEFRAG","${Stock}")
        intent.putExtra("STOCKNAME",Stock.StockName)
        intent.putExtra("COMPANYLOGO",Stock.CompanyLogo)
        intent.putExtra("STOCKPRICE",Stock.StockPrice)
        intent.putExtra("STOCKPERCENTAGE",Stock.StockPercentage)
        intent.putExtra("GRAPHBOOLEAN",Stock.GraphBoolean)
        intent.putExtra("STOCKID",Stock.StockId)
        startActivity(intent)
    }
}