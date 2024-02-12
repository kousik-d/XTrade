package com.intern.xtrade.UserOnBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import com.intern.xtrade.R
import com.intern.xtrade.UserDetails

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetails6.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PersonalDetails6 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var userActivity : UserDetails
    lateinit var PersonalDetails6ContinueBtn : Button

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
        val view = inflater.inflate(R.layout.fragment_personal_details6, container, false)
        userActivity = activity as UserDetails
        PersonalDetails6ContinueBtn = view.findViewById(R.id.personalDetails6_continue)
        PersonalDetails6ContinueBtn.setOnClickListener{
            userActivity.onNextButtonClick(PersonalDetails7())
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
         * @return A new instance of fragment PersonalDetails6.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalDetails6().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}