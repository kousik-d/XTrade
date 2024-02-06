package com.intern.xtrade.RegularAndAMO

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.intern.xtrade.R

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


    lateinit var ProductIntraDay : AppCompatButton
    lateinit var ProductLongTerm : AppCompatButton
    lateinit var TypeMarket : AppCompatButton
    lateinit var TypeLimit : AppCompatButton
    lateinit var TypeSL : AppCompatButton
    lateinit var TypeSLM : AppCompatButton


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

        ProductIntraDay = view.findViewById(R.id.product_intraday)
        ProductLongTerm = view.findViewById(R.id.product_longterm)
        TypeMarket = view.findViewById(R.id.type_market)
        TypeLimit = view.findViewById(R.id.type_limit)
        TypeSL = view.findViewById(R.id.type_sl)
        TypeSLM = view.findViewById(R.id.type_slm)

        ProductIntraDay.setOnClickListener {

        }
        ProductLongTerm.setOnClickListener {
            ProductIntraDay.setBackgroundResource(R.drawable.buy_sell_background_grey)
            ProductIntraDay.setTextColor(ContextCompat.getColor(requireContext(), R.color.darkBlack))
            it.setBackgroundResource(R.drawable.buy_sell_background)
            ProductLongTerm.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
        }

        TypeMarket.setOnClickListener {

        }
        TypeLimit.setOnClickListener {
            TypeMarket.setBackgroundResource(R.drawable.buy_sell_background_grey)
            TypeMarket.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeLimit.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
        }
        TypeSL.setOnClickListener {
            TypeLimit.setBackgroundResource(R.drawable.buy_sell_background_grey)
            TypeLimit.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeSL.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
        }
        TypeSLM.setOnClickListener {
            TypeSL.setBackgroundResource(R.drawable.buy_sell_background_grey)
            TypeSL.setTextColor(ContextCompat.getColor(requireContext(),R.color.darkBlack))
            it.setBackgroundResource(R.drawable.buy_sell_background)
            TypeSLM.setTextColor(ContextCompat.getColor(requireContext(), R.color.card_blue))
        }
        return view
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
}