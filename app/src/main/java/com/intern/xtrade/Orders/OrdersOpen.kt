package com.intern.xtrade.Orders

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.R
import com.intern.xtrade.Repositories.StockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersOpen.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersOpen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var AddOrdersLinearLayout : LinearLayout
    lateinit var stockRepository: StockRepository

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
        val view =  inflater.inflate(R.layout.fragment_orders_open, container, false)
        AddOrdersLinearLayout = view.findViewById(R.id.AddOrdersLinear)
        stockRepository = StockRepository(StockDataBase.invoke(requireContext()))


        stockRepository.stockOrdersOpen.asLiveData().distinctUntilChanged().observe(requireActivity()){
            //Toast.makeText(requireContext(),"${it}",Toast.LENGTH_SHORT).show()

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
         * @return A new instance of fragment OrdersOpen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrdersOpen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun CreateListAndAppendToLayout(totalStockList: List<StockInfo>) {
        AddOrdersLinearLayout.removeAllViews()
        lifecycleScope.launch(Dispatchers.IO) {
            for (stock in totalStockList) {
                val cardView = layoutInflater.inflate(R.layout.order_card, null)
                cardView.findViewById<TextView>(R.id.order_numerator).text = "${1150}"
                cardView.findViewById<TextView>(R.id.order_avgValue).text = "${stock.StockPrice}".substring(0, 3) + ""
                cardView.findViewById<TextView>(R.id.order_companyName).text = stock.CompanyName
                cardView.findViewById<TextView>(R.id.order_status).text = "PENDING"
                cardView.findViewById<TextView>(R.id.order_status).setTextColor(resources.getColor(R.color.card_blue))
                cardView.findViewById<TextView>(R.id.order_status).setBackgroundColor(resources.getColor(R.color.transparent_blue))
                withContext(Dispatchers.Main) {
                    // Set margin for card view
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    object : CountDownTimer(10000,1000){
                        override fun onTick(millisUntilFinished: Long) {
                            Log.i("TIMETODO","${millisUntilFinished}")
                            cardView.findViewById<TextView>(R.id.order_time).text = "00:00:${millisUntilFinished/1000}"
                        }

                        override fun onFinish() {
                            stock.isInOrders = 2
                            stock.isInHoldings = true
                            UpdateOrder(stock)
                        }
                    }.start()
                    layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_Top_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards),
                        resources.getDimensionPixelSize(R.dimen.Ordermargin_between_cards))
                    cardView.layoutParams = layoutParams
                    // Add card view to linear layout
                    AddOrdersLinearLayout.addView(cardView)
                }
            }
        }
    }

    fun UpdateOrder(stockInfo: StockInfo){
        lifecycleScope.launch(IO) {
            stockRepository.updateStock(stockInfo)

        }
    }

}