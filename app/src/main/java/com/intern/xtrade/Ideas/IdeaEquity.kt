package com.intern.xtrade.Ideas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.IPO.BuyIPO
import com.intern.xtrade.IPO.IPOActivity
import com.intern.xtrade.R

/**
 * A simple [Fragment] subclass.
 * Use the [IdeaEquity.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class IdeaEquity : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var IpoEquityViewAll : TextView
    lateinit var IpoCardView : LinearLayout
    lateinit var ShortTermCard : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Equity_clicked")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_idea_equity, container, false)
        IpoEquityViewAll = view.findViewById(R.id.ideaEquity_ipoViewAll)
        IpoCardView = view.findViewById(R.id.ideaEquity_ipoCard)

        IpoCardView.findViewById<Button>(R.id.ipo_apply).setOnClickListener {
            val intent = Intent(requireContext(),BuyIPO::class.java)
            startActivity(intent)
        }
        IpoEquityViewAll.setOnClickListener {
            val intent = Intent(requireContext(),IPOActivity::class.java)

            val attrs = Attributes()
            attrs.putAttribute("Type","IPO")
            ApxorSDK.logAppEvent("Viewall_clicked")

            ApxorSDK.logAppEvent("IPO_clicked")
            startActivity(intent)
        }

        ShortTermCard = view.findViewById(R.id.equity_shortTermInclude)

        ShortTermCard.findViewById<ImageView>(R.id.hidden_arrow_button).setOnClickListener {
            ShortTermCard.findViewById<LinearLayout>(R.id.hidden_layout).visibility = LinearLayout.VISIBLE
            it.visibility = ImageView.GONE
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
         * @return A new instance of fragment IdeaEquity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IdeaEquity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}