package com.intern.xtrade.IPO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.R
import com.intern.xtrade.wishList.WishlistManager
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForthComing.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForthComing : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val indiLocal = Locale("en", "in")
    lateinit var forthComingCardView: CardView
    lateinit var forthComingCardView1: CardView
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd MMM, yy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Forthcoming_IPO_launched")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_forth_coming, container, false)

        forthComingCardView = view.findViewById(R.id.forthcomingCard)
        forthComingCardView1 = view.findViewById(R.id.forthcomingCard1)
        val Indate = outputFormat.format(inputFormat.parse(WishlistManager.IPoData[5].IPOOpenDate))
        val Outdate = outputFormat.format(inputFormat.parse(WishlistManager.IPoData[5].IPOCloseDate))

        val Indate2 = outputFormat.format(inputFormat.parse(WishlistManager.IPoData[6].IPOOpenDate))
        val Outdate2 = outputFormat.format(inputFormat.parse(WishlistManager.IPoData[6].IPOCloseDate))


        forthComingCardView.findViewById<TextView>(R.id.forthcoming_title).text = WishlistManager.IPoData[5].IPOName
        forthComingCardView.findViewById<TextView>(R.id.forthcoming_lotSize).text = WishlistManager.IPoData[5].IPOLotsize.toString()
        forthComingCardView.findViewById<TextView>(R.id.forthcoming_issuePrice).text =
            "₹${WishlistManager.IPoData[5].IPOMinPrice} - ${WishlistManager.IPoData[5].IPOMaxPrice}"
        forthComingCardView.findViewById<TextView>(R.id.forthcoming_date).text = "${Indate} - ${Outdate}"
        forthComingCardView.findViewById<TextView>(R.id.forthcoming_lotSize).text = WishlistManager.IPoData[5].IPOLotsize.toString()

        forthComingCardView1.findViewById<TextView>(R.id.forthcoming_title).text = WishlistManager.IPoData[6].IPOName
        forthComingCardView1.findViewById<TextView>(R.id.forthcoming_lotSize).text = WishlistManager.IPoData[6].IPOLotsize.toString()
        forthComingCardView1.findViewById<TextView>(R.id.forthcoming_issuePrice).text =
            "₹${WishlistManager.IPoData[5].IPOMinPrice} - ${WishlistManager.IPoData[6].IPOMaxPrice}"
        forthComingCardView1.findViewById<TextView>(R.id.forthcoming_date).text = "${Indate2} - ${Outdate2}"
        forthComingCardView1.findViewById<TextView>(R.id.forthcoming_lotSize).text = WishlistManager.IPoData[6].IPOLotsize.toString()

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForthComing.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForthComing().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}