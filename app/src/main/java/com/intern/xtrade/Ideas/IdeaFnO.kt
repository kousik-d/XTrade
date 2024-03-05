package com.intern.xtrade.Ideas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.apxor.androidsdk.core.ApxorSDK
import com.apxor.androidsdk.core.Attributes
import com.intern.xtrade.R

/**
 * A simple [Fragment] subclass.
 * Use the [IdeaFnO.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class IdeaFnO : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Fno_clicked")
        ApxorSDK.trackScreen("Fno_clicked")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var nifty = "Bearish"
        var bankNifty = "Bearish"
        val view = inflater.inflate(R.layout.fragment_idea_fn_o, container, false)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = selectedRadioButtonId.let { view.findViewById<RadioButton>(it) }
            nifty = selectedRadioButton.text.toString()
        }

        val radioGroup2 = view.findViewById<RadioGroup>(R.id.radioGroup2)
        val selectedRadioButtonId2 = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId2 != -1) {
            val selectedRadioButton = selectedRadioButtonId2.let { view.findViewById<RadioButton>(it) }
            bankNifty = selectedRadioButton.text.toString()
        }

        val attrs = Attributes()
        attrs.putAttribute("Nifty",nifty)
        attrs.putAttribute("Bank Nifty",bankNifty)
        ApxorSDK.logAppEvent("Quick_option_selected",attrs)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IdeaFnO.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IdeaFnO().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}