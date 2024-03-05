package com.intern.xtrade.IPO


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.Orders.OrderConfirmed
import com.intern.xtrade.R
import com.intern.xtrade.RegularAndAMO.BuyRegularActivity
import com.intern.xtrade.wishList.WishlistManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class BuyIPO : AppCompatActivity() {

    lateinit var IPOBackImage : ImageView
    lateinit var inverstortypeSpinner : Spinner
    var investorType = arrayListOf<String>("Individual","Employee")
    lateinit var addMultipleBidsBtn : AppCompatButton
    lateinit var addBidsLayout : LinearLayout
    lateinit var BidOneQunatity : EditText
    lateinit var BidOnePrice : EditText
    lateinit var BidOneCardview : CardView
    lateinit var IPOName : TextView
    lateinit var IPOLogo : ImageView
    lateinit var IPOOpenDate : TextView
    lateinit var IPOCloseDate : TextView
    lateinit var IPOApply : AppCompatButton
    lateinit var IPOProgressBar : ProgressBar
    var count = 2
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM, yy", Locale.ENGLISH)
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
        IPOName = findViewById(R.id.IPO_name)
        IPOLogo = findViewById(R.id.IPO_logo)
        IPOOpenDate = findViewById(R.id.IPO_openDate)
        IPOCloseDate = findViewById(R.id.IPO_closeDate)


        IPOApply = findViewById<LinearLayout>(R.id.ButtonLinearLayout).findViewById(R.id.IPO_Apply)
        IPOProgressBar = findViewById(R.id.IPO_ProgressBar)

        IPOProgressBar.visibility = ProgressBar.INVISIBLE





        var ipo = intent.getSerializableExtra("IPODATA")
        val data: Uri? = intent.data
        data?.let {
            // Extract query parameters
            val ipoName = it.getQueryParameter("ipoName")
            var newIpo = ipoName?.replace("_"," ")

            val totalIpos = RandomIPOData()
            Log.i("IPODATA","${newIpo}")
            val index = totalIpos.indexOfFirst { it.IPOName == newIpo }
            if(index!=-1){

                FillDataWithIPOData(totalIpos[index])
            }
            // Extract other parameters as needed
            Log.i("PARAM","${ipoName}")

        }
        if(ipo!=null) {
            FillDataWithIPOData(ipo as IPOData)
        }

        IPOApply.setOnClickListener {

            IPOApply.text =""
            IPOProgressBar.visibility = ProgressBar.VISIBLE
            Toast.makeText(this,"Your Bid is being processed",Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                finish()
            },3000)

        }


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

    private fun FillDataWithIPOData(ipo: IPOData) {
        IPOName.text = ipo.IPOName
        IPOLogo.setImageResource(ipo.IPOImage)
        val Indate = outputFormat.format(inputFormat.parse(ipo.IPOOpenDate))
        val Outdate = outputFormat.format(inputFormat.parse(ipo.IPOCloseDate))
        IPOOpenDate.text = Indate
        IPOCloseDate.text = Outdate
        BidOneCardview.findViewById<TextView>(R.id.LotSize2).text = "Lot size: ${ipo.IPOLotsize}"
        BidOneCardview.findViewById<EditText>(R.id.Quantity).hint = ipo.IPOLotsize.toString()
        BidOneCardview.findViewById<EditText>(R.id.Price).hint = ipo.IPOMinPrice
        findViewById<TextView>(R.id.IPO_MinPrice).text = ipo.IPOMinPrice
        findViewById<TextView>(R.id.IPO_maxPrice).text = ipo.IPOMaxPrice
        findViewById<TextView>(R.id.IPO_lotSize).text = ipo.IPOLotsize.toString()

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
        ApxorSDK.trackScreen("IPO_apply_now_clicked")
    }
    fun RandomIPOData(): MutableList<IPOData> {
        val IPODataList = mutableListOf<IPOData>()
        val IPONames = arrayOf("Juniper Hotels","GPT Healthcare","Platinum Industries","Vibhor Steel Tubes","Rashi Peripherals","Nova AgriTech", "BLS E-Services")
        val BSEorNSE = arrayOf("NSE","BSE","BSE","NSE","NSE","BSE","NSE")
        val MinPrice = arrayOf(340.00,177.00,150.00,280.00,414.00,370.00,41.00)
        val MaxPrice = arrayOf(362.00,182.00,159.00,292.00,424.00,380.00,47.00)
        val LotSize = arrayOf(99,80,45,36,70,96,66)
        val IPOLogos = arrayOf(
            R.drawable.juniper_hotels_logo_image,
            R.drawable.gpt_healthcare_logo,
            R.drawable.platinum_logo,
            R.drawable.vibhor_steel_tubes_logo,
            R.drawable.rashi_peripherals_logo,
            R.drawable.nova_agritech_ipo,
            R.drawable.bls_e_services_ipo_gmp)

        val startDate = arrayListOf<Date>()
        val endDate = arrayListOf<Date>()

        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        calendar.add(Calendar.DATE, -5)
        val fiveDaysBefore = calendar.time

        repeat(3) {
            val randomStartDate = generateRandomDate(fiveDaysBefore, currentDate)
            startDate.add(randomStartDate)
        }

        calendar.time = currentDate
        calendar.add(Calendar.DATE, 5)
        val fiveDaysAfter = calendar.time

        repeat(4) {
            val randomStartDate = generateRandomDate(currentDate, fiveDaysAfter)
            startDate.add(randomStartDate)
        }

        startDate.forEach { start ->
            val randomEndDate = generateRandomEndDate(start)
            endDate.add(randomEndDate)
        }

        startDate.forEachIndexed { index, date ->
            val message = "Entry ${index + 1}: Start Date: $date, End Date: ${endDate[index]}"
            Log.d("DateGeneration", message) // Log the message
        }

        for(i in 0..6){
            val ipodata = IPOData(
                IPOId = i,
                IPOName = IPONames[i],
                IPOImage = IPOLogos[i],
                IPOOpenDate = startDate[i].toString(),
                IPOCloseDate = endDate[i].toString(),
                IPOMinPrice = MinPrice[i].toString(),
                IPOMaxPrice = MaxPrice[i].toString(),
                IPOLotsize = LotSize[i],
                isIPOOpen = false,
                isApplied = false,
                isForthComing = false,
                MinInvestments = Random.nextInt(9000,15000)
            )
            IPODataList.add(ipodata)
        }
        return IPODataList
    }
    fun generateRandomDate(startDate: Date, endDate: Date): Date {
        val startMillis = startDate.time
        val endMillis = endDate.time
        val randomMillisSinceEpoch = Random.nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }

    fun generateRandomEndDate(startDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = startDate
        calendar.add(Calendar.DATE, Random.nextInt(4, 9))
        return calendar.time
    }
}

