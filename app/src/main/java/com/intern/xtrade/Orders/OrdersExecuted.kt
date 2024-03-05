package com.intern.xtrade.Orders

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SharedMemory
import android.util.Log
import android.view.DisplayShape
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import com.intern.xtrade.Repositories.StockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersExecuted.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersExecuted : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var stockRepository: StockRepository
    lateinit var addtoOrderExecuted : LinearLayout
    lateinit var sharedPreferences: SharedPreferences
    var value = 0.0f;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("Orders_Executed");
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orders_executed, container, false)
        addtoOrderExecuted = view.findViewById(R.id.AddtoOrderExecuted)
        stockRepository = StockRepository(StockDataBase.invoke(requireContext()))
        sharedPreferences = requireContext().getSharedPreferences("MONEY", Context.MODE_PRIVATE)
        val inital  = sharedPreferences.getFloat("INVESTEDVALUE",0.0f)
        stockRepository.stockOrdersExecuted.asLiveData().distinctUntilChanged().observe(requireActivity()){
            CreateListAndAppendToLayout(it)

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
         * @return A new instance of fragment OrdersExecuted.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrdersExecuted().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun CreateListAndAppendToLayout(totalStockList: List<StockInfo>) {
        lifecycleScope.launch(Dispatchers.IO) {
            for (stock in totalStockList) {
                val cardView = layoutInflater.inflate(R.layout.order_card, null)
                cardView.findViewById<TextView>(R.id.order_numerator).text = "1150"
                cardView.findViewById<TextView>(R.id.order_avgValue).text = "${stock.StockPrice}".substring(0, 3) + ""
                cardView.findViewById<TextView>(R.id.order_companyName).text = stock.CompanyName
                cardView.findViewById<TextView>(R.id.order_status).text = "COMPLETED"
                cardView.findViewById<TextView>(R.id.order_time).visibility = TextView.INVISIBLE
                cardView.findViewById<TextView>(R.id.order_status).setTextColor(resources.getColor(R.color.green))
                cardView.findViewById<TextView>(R.id.order_status).setBackgroundColor(resources.getColor(R.color.transparent_green))

                withContext(Dispatchers.Main) {
                    // Set margin for card view
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_Top_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards))
                    cardView.layoutParams = layoutParams
                    // Add card view to linear layout
                    addtoOrderExecuted.addView(cardView)
                }
            }
        }
    }
}