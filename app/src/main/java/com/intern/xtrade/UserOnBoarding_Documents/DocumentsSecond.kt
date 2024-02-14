package com.intern.xtrade.UserOnBoarding_Documents

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.intern.xtrade.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DocumentsSecond.newInstance] factory method to
 * create an instance of this fragment.
 */
class DocumentsSecond : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var showCardView : CardView
    lateinit var DocumentsSecondFinishBtn : AppCompatButton
    lateinit var DocumentsSecondSwitch : Switch
    lateinit var sharedPreferences : SharedPreferences

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
        val view = inflater.inflate(R.layout.fragment_documents_second, container, false)
        showCardView = view.findViewById(R.id.ShowCardView)
        DocumentsSecondFinishBtn = view.findViewById(R.id.documents_continue)
        DocumentsSecondSwitch = view.findViewById(R.id.documents_switch)
        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 4).apply()


        DocumentsSecondSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                showCardView.visibility = CardView.VISIBLE
            }else{
                showCardView.visibility = CardView.INVISIBLE
            }
        }

        DocumentsSecondFinishBtn.setOnClickListener {
            val intent = Intent(requireContext(),SignUpSuccessful::class.java)
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
         * @return A new instance of fragment DocumentsSecond.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentsSecond().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}