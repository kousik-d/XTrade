package com.intern.xtrade.IPO

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.DataClasses.StockInfo

import com.intern.xtrade.R
import com.intern.xtrade.Repositories.IPORepository
import com.intern.xtrade.StockScreen
import com.intern.xtrade.wishList.WishlistManager
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Use the [LiveIPOS.newInstance] factory method to
 * create an instance of this fragment.
 */
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LiveIPOS : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//    lateinit var ApplyNowBtn : AppCompatButton
    lateinit var liveIPOLinearLayout : LinearLayout
    val indiLocal = Locale("en", "in")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Live_IPO_launched")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_live_i_p_o_s, container, false)

        liveIPOLinearLayout = view.findViewById(R.id.AddLiveIPO)
//        ApplyNowBtn = IPOCardView.findViewById(R.id.ipo_apply)
//
//        ApplyNowBtn.setOnClickListener {
//            val intent = Intent(requireContext(),BuyIPO::class.java)
//            startActivity(intent)
//        }
        val list = WishlistManager.IPoData
        CreateListAndAppendToLayout(list.dropLast(2))

        return view
    }

    private fun CreateListAndAppendToLayout(IPOList: List<IPOData>) {
        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd MMM, yy", Locale.ENGLISH)
        for (ipo in IPOList) {
            val cardView = layoutInflater.inflate(R.layout.live_ipo_card, null)
            cardView.findViewById<TextView>(R.id.ipo_title).text = "${ipo.IPOName}"
            cardView.findViewById<TextView>(R.id.ipo_issuePrice).text = "₹${ipo.IPOMinPrice} - ${ipo.IPOMaxPrice}"
            val Indate = outputFormat.format(inputFormat.parse(ipo.IPOOpenDate))
            val Outdate = outputFormat.format(inputFormat.parse(ipo.IPOCloseDate))
            val money = NumberFormat.getCurrencyInstance(indiLocal).format(ipo.MinInvestments)
            cardView.findViewById<TextView>(R.id.ipo_noOfShares).text = "/"+ipo.IPOLotsize+" Shares"
            cardView.findViewById<TextView>(R.id.ipo_minInvestments).text = money.dropLast(3)
            cardView.findViewById<TextView>(R.id.ipo_date).text=
                "${Indate} - ${Outdate}"

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            cardView.findViewById<AppCompatButton>(R.id.ipo_apply).setOnClickListener {
                navigationationToIpoData(ipo)
            }
            layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen.margin_between_cards), 0, resources.getDimensionPixelSize(R.dimen.margin_between_cards), resources.getDimensionPixelSize(R.dimen.margin_between_cards))
            cardView.layoutParams = layoutParams
            // Add card view to linear layout
            liveIPOLinearLayout.addView(cardView)
        }
    }

    private fun navigationationToIpoData(ipo: IPOData) {
        val intent = Intent(requireContext(), BuyIPO::class.java)

        intent.putExtra("IPODATA",ipo)
//        intent.putExtra("STOCKNAME",Stock.StockName)
//        intent.putExtra("COMPANYLOGO",Stock.CompanyLogo)
//        intent.putExtra("STOCKPRICE",Stock.StockPrice)
//        intent.putExtra("STOCKPERCENTAGE",Stock.StockPercentage)
//        intent.putExtra("GRAPHBOOLEAN",Stock.GraphBoolean)
//        intent.putExtra("STOCKID",Stock.StockId)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LiveIPOS.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LiveIPOS().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}