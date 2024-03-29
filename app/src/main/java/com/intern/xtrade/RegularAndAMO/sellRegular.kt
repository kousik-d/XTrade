package com.intern.xtrade.RegularAndAMO

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Orders.OrderConfirmed
import com.intern.xtrade.PaymentSuccessActivity
import com.intern.xtrade.R
import com.intern.xtrade.Repositories.StockRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [sellRegular.newInstance] factory method to
 * create an instance of this fragment.
 */
class sellRegular : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var PriceAmount : EditText
    lateinit var StockQuantity : EditText
    lateinit var ProductIntraDay : AppCompatButton
    lateinit var ProductLongTerm : AppCompatButton
    lateinit var TypeMarket : AppCompatButton
    lateinit var TypeLimit : AppCompatButton
    lateinit var TypeSL : AppCompatButton
    lateinit var TypeSLM : AppCompatButton
    lateinit var ValidityDay : AppCompatButton
    lateinit var ValidityIOC : AppCompatButton
    lateinit var ValidityMin : AppCompatButton
    lateinit var PurchaseButton : AppCompatButton
    lateinit var stockRepository: StockRepository
    lateinit var DiscQtyqt : EditText
    lateinit var DiscQtSpinner : Spinner
    lateinit var PurchaseProgressBar : ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesAttr: SharedPreferences
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Sell_regular")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sell_regularamo, container, false)

        var eventProduct = "NA"
        var eventType = "NA"
        var eventValidity = "NA"
        var eventScreen = "Regular"
        var eventQty = "0"
        var eventPrice = 0.0f

        ProductIntraDay = view.findViewById(R.id.sell_product_intraday)
        ProductLongTerm = view.findViewById(R.id.sell_product_longterm)
        TypeMarket = view.findViewById(R.id.sell_type_market)
        TypeLimit = view.findViewById(R.id.sell_type_limit)
        TypeSL = view.findViewById(R.id.sell_type_sl)
        TypeSLM = view.findViewById(R.id.sell_type_slm)
        ValidityDay = view.findViewById(R.id.sell_validity_day)
        ValidityIOC = view.findViewById(R.id.sell_validity_ioc)
        ValidityMin = view.findViewById(R.id.sell_validity_minutes)
        DiscQtyqt = view.findViewById(R.id.validity_left_value)
        DiscQtSpinner =view.findViewById(R.id.validity_dropdown)


        PriceAmount = view.findViewById(R.id.sell_price)
        StockQuantity = view.findViewById(R.id.sell_TotalQuantity)
        PurchaseButton = view.findViewById(R.id.SellButtonId)
        PurchaseProgressBar = view.findViewById(R.id.SellLoadProgressBar)
        stockRepository = StockRepository(StockDataBase.invoke(requireContext()))
        PurchaseProgressBar.visibility = ProgressBar.INVISIBLE


        sharedPreferences = requireContext().getSharedPreferences("MONEY",Context.MODE_PRIVATE)
        sharedPreferencesAttr = requireContext().getSharedPreferences("USERATTR",Context.MODE_PRIVATE)


        val totalMoney = sharedPreferencesAttr.getFloat("TOTALSTOCKMONEY",0.0f)
        count = sharedPreferencesAttr.getInt("STOCKSELLCOUNT",0)
        Log.i("COUNTSELLOT","${count}")

        var investedValue = sharedPreferences.getFloat("INVESTEDVALUE",0.0f)
        var sellPrice = 0.0f
        PurchaseButton.setOnClickListener {
            eventQty = StockQuantity.text.toString()

            PurchaseButton.text =""
            PurchaseProgressBar.visibility = ProgressBar.VISIBLE
            PurchaseButton.isEnabled = false
            count= count+1
            Log.i("COUNTSELL","${count}")
            sharedPreferencesAttr.edit().putInt("STOCKSELLCOUNT",count).apply()
            var sellPrice = sharedPreferencesAttr.getFloat("STOCKSELLMoney",0.0f)
            sellPrice = sellPrice+sellRegular.sellStockPrice
            sharedPreferencesAttr.edit().putFloat("STOCKSELLMoney",sellPrice).apply()
            stockRepository.allStocks.distinctUntilChanged().observe(requireActivity()){
                val totalList = it
                for(i in it){
                    if(i.StockId == sellRegular.sellStockId){
                        i.isInHoldings = false
                        sellPrice = i.StockPrice.toFloat()
                        investedValue -= sellPrice
                        sharedPreferences.edit().putFloat("INVESTEDVALUE",investedValue).apply()
                        Log.i("InvestedValueSell","${investedValue} ${sellPrice} ")
                        UpdateSoldStockInDb(i)
                    }
                }
            }
            Handler().postDelayed({
                val intent = Intent(requireContext(), PaymentSuccessActivity::class.java)

                val attr = Attributes()
                attr.putAttribute("Product",eventProduct)
                attr.putAttribute("Type",eventType)
                attr.putAttribute("Validity",eventValidity)
                attr.putAttribute("Quantity",eventQty)
                attr.putAttribute("Price",eventPrice)
                attr.putAttribute("Screen",eventScreen)
                ApxorSDK.logAppEvent("Sell_stock",attr)

                intent.putExtra("SELLSTOCKID",sellRegular.sellStockId)
                startActivity(intent)
            },3000)
        }

//        Log.i("InvestedValueSellOut","${investedValue} ${sellPrice}")
//        investedValue-=sellPrice
//        sharedPreferences.edit().putFloat("INVESTEDVALUE",investedValue).apply()

        PriceAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this implementation
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed for this implementation
            }
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.isNotEmpty()) {
                    val value = text.toDoubleOrNull()
                    if (value != null && value % 0.05 != 0.0) {
                        Toast.makeText(
                            requireContext(),
                            "Enter valid value (Tick size: 0.05)",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Clear the EditText or perform any other action
                        PriceAmount.text.clear()
                    }
                    else if (value != null) {
                        eventPrice = value.toFloat()
                    }
                }
            }
        })



// Define the options
        val options = arrayOf(
            "1 minute",
            "2 minutes",
            "3 minutes",
            "5 minutes",
            "10 minutes",
            "15 minutes",
            "30 minutes",
            "45 minutes",
            "60 minutes",
            "90 minutes",
            "120 minutes",
        )

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        DiscQtSpinner.adapter = adapter

        ValidityDay.setOnClickListener {
            eventValidity = "Day"
            SetValidityBackground()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            ValidityDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            DiskVisibility()
            TypeVisibility()
            DiscQtSpinner.visibility = EditText.INVISIBLE
        }

        ValidityMin.setOnClickListener {
            eventValidity = "Minutes"

            SetValidityBackground()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            ValidityMin.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            DiskVisibility()
            TypeVisibility()
        }

        ValidityIOC.setOnClickListener {
            eventValidity = "IOC"

            SetValidityBackground()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            ValidityIOC.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            TypeSL.visibility = Button.INVISIBLE
            TypeSLM.visibility = Button.INVISIBLE
            DiscQtyqt.visibility = EditText.INVISIBLE
            DiscQtSpinner.visibility = EditText.INVISIBLE
        }



        ProductIntraDay.setOnClickListener {
            eventProduct = "Intraday"


            it.setBackgroundResource(R.drawable.sell_stock_button)
            ProductIntraDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))
            ProductLongTerm.setBackgroundResource(R.drawable.buy_sell_background)
            ProductLongTerm.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlack))

        }
        ProductLongTerm.setOnClickListener {
            eventProduct = "Long term CNC"

            ProductIntraDay.setBackgroundResource(R.drawable.buy_sell_background_grey)
            ProductIntraDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlack))
            it.setBackgroundResource(R.drawable.sell_stock_button)
            ProductLongTerm.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))
        }

        TypeMarket.setOnClickListener {
            eventType = "Market"

            ReplaceTriggerLayout()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            TypeMarket.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))


            AllVisibility()
            PriceAmount.visibility = EditText.INVISIBLE
        }
        TypeLimit.setOnClickListener {
            eventType = "Limit"

            ReplaceTriggerLayout()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            TypeLimit.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            AllVisibility()
        }
        TypeSL.setOnClickListener {
            eventType = "SL"

            LoadTrigger()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            TypeSL.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            AllVisibility()
            ValidityIOC.visibility =  Button.INVISIBLE
        }
        TypeSLM.setOnClickListener {
            eventType = "SL-M"

            LoadTrigger()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.sell_stock_button)
            TypeSLM.setTextColor(ContextCompat.getColor(requireContext(), R.color.SellColor))

            AllVisibility()
            PriceAmount.visibility = EditText.INVISIBLE
            ValidityIOC.visibility = Button.INVISIBLE

        }

        return view
    }

    private fun SetValidityBackground() {
        ValidityDay.setBackgroundResource(R.drawable.buy_sell_background_grey)
        ValidityDay.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
        ValidityMin.setBackgroundResource(R.drawable.buy_sell_background_grey)
        ValidityMin.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
        ValidityIOC.setBackgroundResource(R.drawable.buy_sell_background_grey)
        ValidityIOC.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))

    }

    private fun AllVisibility(){
        TypeMarket.visibility = Button.VISIBLE
        TypeLimit.visibility = Button.VISIBLE
        TypeSL.visibility = Button.VISIBLE
        TypeSLM.visibility = Button.VISIBLE
        PriceAmount.visibility = EditText.VISIBLE
        ValidityIOC.visibility = Button.VISIBLE
    }

    private fun TypeVisibility(){
        TypeMarket.visibility = Button.VISIBLE
        TypeLimit.visibility = Button.VISIBLE
        TypeSL.visibility = Button.VISIBLE
        TypeSLM.visibility = Button.VISIBLE
    }

    private fun DiskVisibility(){
        DiscQtyqt.visibility = EditText.VISIBLE
        DiscQtSpinner.visibility= Spinner.VISIBLE
    }

    private fun ValidityVisibility(){

        ValidityDay.visibility = Button.VISIBLE
        ValidityMin.visibility = Button.VISIBLE
        ValidityIOC.visibility = Button.VISIBLE
        DiscQtyqt.visibility = EditText.VISIBLE
        DiscQtSpinner.visibility= Spinner.VISIBLE
    }

    private fun ReplaceTriggerLayout(){
        val frag = childFragmentManager.findFragmentById(R.id.FrameTrigger)
        frag?.let {
            childFragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }
    }

    private fun LoadTrigger() {
        childFragmentManager.beginTransaction()
            .replace(R.id.FrameTrigger,TriggerFragment())
            .commit()
    }


    private fun ReplaceAlltheBackGroundtoGrey() {
        TypeSL.setBackgroundResource(R.drawable.buy_sell_background_grey)
        TypeSL.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
        TypeMarket.setBackgroundResource(R.drawable.buy_sell_background_grey)
        TypeMarket.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
        TypeLimit.setBackgroundResource(R.drawable.buy_sell_background_grey)
        TypeLimit.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
        TypeSLM.setBackgroundResource(R.drawable.buy_sell_background_grey)
        TypeSLM.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TriggerFragment.
         */
        // TODO: Rename and change types and number of parameters

        var sellStockId = -1
        var sellStockPrice = 0.0f
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            sellRegular().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun UpdateSoldStockInDb(stockInfo: StockInfo){
        lifecycleScope.launch(IO) {
            stockRepository.updateStock(stockInfo)
        }
    }
}