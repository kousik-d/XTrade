package com.intern.xtrade.IPO

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PastBids.newInstance] factory method to
 * create an instance of this fragment.
 */
class PastBids : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var pastBidCardView: CardView
    lateinit var pastBidCardView1: CardView
    lateinit var pastBidCardView2: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Past_bids_launched")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_past_bids, container, false)
        pastBidCardView = view.findViewById(R.id.pastBidsCard)
        pastBidCardView1 = view.findViewById(R.id.pastBidsCard1)
        pastBidCardView2 = view.findViewById(R.id.pastBidsCard2)

        pastBidCardView.findViewById<TextView>(R.id.pastBids_status).text = "Accepted"
        pastBidCardView.findViewById<TextView>(R.id.pastBids_status).setTextColor(requireContext().resources.getColor(
            R.color.green
        ))

        pastBidCardView2.findViewById<TextView>(R.id.pastBids_title).text ="Capital Finance Bank Ltd."
        pastBidCardView2.findViewById<TextView>(R.id.pastBids_status).text = "Accepted"
        pastBidCardView2.findViewById<TextView>(R.id.pastBids_appNumber).text ="800003623"
        pastBidCardView2.findViewById<TextView>(R.id.pastBids_status).setTextColor(requireContext().resources.getColor(
            R.color.green
        ))


        pastBidCardView1.findViewById<TextView>(R.id.pastBids_title).text ="Exicom Tele-Systems Ltd."
        pastBidCardView1.findViewById<TextView>(R.id.pastBids_appNumber).text ="800003781"

        pastBidCardView1.findViewById<TextView>(R.id.pastBids_status).text = "Rejected"
        pastBidCardView1.findViewById<TextView>(R.id.pastBids_status).setTextColor(requireContext().resources.getColor(
            R.color.red
        ))
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PastBids.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PastBids().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}