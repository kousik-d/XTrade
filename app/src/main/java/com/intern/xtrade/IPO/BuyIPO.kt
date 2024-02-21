package com.intern.xtrade.IPO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.R

class BuyIPO : AppCompatActivity() {

    lateinit var IPOBackImage : ImageView
    lateinit var inverstortypeSpinner : Spinner
    var investorType = arrayListOf<String>("Individual","Employee")
    lateinit var addMultipleBidsBtn : AppCompatButton
    lateinit var addBidsLayout : LinearLayout
    lateinit var BidOneQunatity : EditText
    lateinit var BidOnePrice : EditText
    lateinit var BidOneCardview : CardView
    var count = 2
    var priceToDisplay = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ipo)
        IPOBackImage = findViewById(R.id.IPO_back)
        inverstortypeSpinner = findViewById(R.id.InvestorTypeSpinner)
        addMultipleBidsBtn = findViewById(R.id.AddMultiplebidsBtn)
        BidOneCardview = findViewById(R.id.Bid1)
        addBidsLayout = findViewById(R.id.bidContainer)
        BidOneQunatity = BidOneCardview.findViewById(R.id.Quantity)
        BidOnePrice = BidOneCardview.findViewById(R.id.Price)

        BidOneQunatity.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                
            }

        })



        addMultipleBidsBtn.setOnClickListener {
            val CreateView  = createABidView(count)
            addBidsLayout.addView(CreateView)
            count++
        }

        IPOBackImage.setOnClickListener {
            finish()
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, investorType)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        inverstortypeSpinner.adapter = adapter

    }

    private fun createABidView(count: Int): View {
        val view = layoutInflater.inflate(R.layout.ipo_bid_card,null)
        view.findViewById<TextView>(R.id.bidTextView).text = "BID ${count}"
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.IPOmargin_between_cards),
            0,
            resources.getDimensionPixelSize(R.dimen.IPOmargin_between_cards),
            resources.getDimensionPixelSize(R.dimen.IPOmargin_between_cards))
        view.layoutParams = layoutParams
        return view
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("IPO_apply_now_clicked")
    }
}