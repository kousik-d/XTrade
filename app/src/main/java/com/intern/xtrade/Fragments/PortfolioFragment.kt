package com.intern.xtrade.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.intern.xtrade.FinalPayment
import com.intern.xtrade.R

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

        DepositINRbtn.setOnClickListener {
<<<<<<< HEAD
<<<<<<< HEAD
            val intent :Intent = Intent(requireContext(),DepositINRActivity::class.java)
            startActivity(intent)
=======


>>>>>>> eaa839231dd42db111e140c41d10efed3effa036
=======


>>>>>>> eaa839231dd42db111e140c41d10efed3effa036
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
    }
}