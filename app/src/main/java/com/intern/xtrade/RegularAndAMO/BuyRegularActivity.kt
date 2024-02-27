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
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import com.intern.xtrade.Orders.OrderConfirmed
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
 * Use the [BuyRegularActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyRegularActivity : Fragment() {
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
    lateinit var sharedPreferences: SharedPreferences

    lateinit var DiscQtyqt : EditText
    lateinit var DiscQtSpinner : Spinner
    lateinit var PurchaseProgressBar : ProgressBar
    lateinit var stockRepository: StockRepository

    //Flags

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            Log.i("KOUSIKDASARIFRAG","ISOKONCREATE")
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_buy_regular_activity, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MONEY",Context.MODE_PRIVATE)

        var eventProduct = "NA"
        var eventType = "NA"
        var eventValidity = "NA"
        var eventScreen = "Regular"
        var eventQty = "0"
        var eventPrice = 0.0f


        ProductIntraDay = view.findViewById(R.id.product_intraday)
        ProductLongTerm = view.findViewById(R.id.product_longterm)
        TypeMarket = view.findViewById(R.id.type_market)
        TypeLimit = view.findViewById(R.id.type_limit)
        TypeSL = view.findViewById(R.id.type_sl)
        TypeSLM = view.findViewById(R.id.type_slm)
        ValidityDay = view.findViewById(R.id.validity_day)
        ValidityIOC = view.findViewById(R.id.validity_ioc)
        ValidityMin = view.findViewById(R.id.validity_minutes)
        DiscQtyqt = view.findViewById(R.id.validity_left_value)
        DiscQtSpinner =view.findViewById(R.id.validity_dropdown)
        PriceAmount = view.findViewById(R.id.buy_Price)
        StockQuantity = view.findViewById(R.id.buy_TotalQuantity)
        PurchaseButton = view.findViewById(R.id.PurchaseButtonId)
        PurchaseProgressBar = view.findViewById(R.id.PurchaseLoadBtn)

        stockRepository = StockRepository(StockDataBase.invoke(requireContext()))
        PurchaseProgressBar.visibility = ProgressBar.INVISIBLE

        PurchaseButton.setOnClickListener {
            eventQty = StockQuantity.text.toString()
            PurchaseButton.text =""
            PurchaseProgressBar.visibility = ProgressBar.VISIBLE
            PurchaseButton.isEnabled = false
            Handler().postDelayed({
                UpdateStockinDb(BuyRegularActivity.PurchasedStockId)
                val intent = Intent(requireContext(),OrderConfirmed::class.java)

                val attr = Attributes()
                attr.putAttribute("Product",eventProduct)
                attr.putAttribute("Type",eventType)
                attr.putAttribute("Validity",eventValidity)
                attr.putAttribute("Quantity",eventQty)
                attr.putAttribute("Price",eventPrice)
                attr.putAttribute("Screen",eventScreen)
                ApxorSDK.logAppEvent("Buy_stock",attr)


                intent.putExtra("STOCKID",BuyRegularActivity.PurchasedStockId)
                val invested = sharedPreferences.getFloat("INVESTEDVALUE",0.0f)
                sharedPreferences.edit().putFloat("INVESTEDVALUE",invested+(BuyRegularActivity.stockPrice)).apply()
                startActivity(intent)
            },3000)
        }

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
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ValidityDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

            DiskVisibility()
            TypeVisibility()
            DiscQtSpinner.visibility = EditText.INVISIBLE
        }

        ValidityMin.setOnClickListener {
            eventValidity = "Minutes"

            SetValidityBackground()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ValidityMin.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

            DiskVisibility()
            TypeVisibility()
        }

        ValidityIOC.setOnClickListener {
            eventValidity = "IOC"

            SetValidityBackground()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ValidityIOC.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

            TypeSL.visibility = Button.INVISIBLE
            TypeSLM.visibility = Button.INVISIBLE
            DiscQtyqt.visibility = EditText.INVISIBLE
            DiscQtSpinner.visibility = EditText.INVISIBLE
        }



        ProductIntraDay.setOnClickListener {

            eventProduct = "IntraDay"
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ProductIntraDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
            ProductLongTerm.setBackgroundResource(R.drawable.buy_sell_background_grey)
            ProductLongTerm.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlack))

        }
        ProductLongTerm.setOnClickListener {
            eventProduct = "Long term CNC"

            ProductIntraDay.setBackgroundResource(R.drawable.buy_sell_background_grey)
            ProductIntraDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlack))
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ProductLongTerm.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
        }

        TypeMarket.setOnClickListener {

            eventType = "Market"
            ReplaceTriggerLayout()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeMarket.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))


            AllVisibility()
            PriceAmount.visibility = EditText.INVISIBLE
        }
        TypeLimit.setOnClickListener {

            eventType = "Limit"

            ReplaceTriggerLayout()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeLimit.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

            AllVisibility()
        }
        TypeSL.setOnClickListener {
            eventType = "SL"

            LoadTrigger()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeSL.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

            AllVisibility()
            ValidityIOC.visibility =  Button.INVISIBLE
        }
        TypeSLM.setOnClickListener {
            eventType = "SL-M"

            LoadTrigger()
            ReplaceAlltheBackGroundtoGrey()
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeSLM.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))

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

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BuyRegularActivity.
         */

        var PurchasedStockId = 1
        var stockPrice = 0.0f
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BuyRegularActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun UpdateStockinDb(stockId: Int) {
        stockRepository.allStocks.observe(requireActivity()){
            val list = it
            val index = list.indexOfFirst{ it.StockId == stockId }
            if(index!=-1){
                val newStock = list[index]
                newStock.isInOrders = 1
                updateDb(newStock)
            }
        }
    }

    fun updateDb(newStock : StockInfo){
        lifecycleScope.launch(IO) {
            stockRepository.updateStock(newStock)
        }
    }
}