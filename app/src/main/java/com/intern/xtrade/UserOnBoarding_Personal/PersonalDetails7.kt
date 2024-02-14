package com.intern.xtrade.UserOnBoarding_Personal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.intern.xtrade.MainActivity

import com.intern.xtrade.R
import com.intern.xtrade.UserDetails
import com.intern.xtrade.UserOnBoarding_Documents.DocumentsFirst

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetails7.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PersonalDetails7 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var userActivity : UserDetails
    private val buttons = mutableListOf<Button>()
    lateinit var PersonalDetails7ContinueBtn : Button
    var isButtonClicked = false
    lateinit var checkBoxOne : CheckBox
    lateinit var checkBoxTwo : CheckBox
    lateinit var sharedPreferences: SharedPreferences

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
        val view =  inflater.inflate(R.layout.fragment_personal_details7, container, false)
        userActivity = activity as UserDetails
        PersonalDetails7ContinueBtn = view.findViewById(R.id.personalDetails7_continue)

        checkBoxOne = view.findViewById(R.id.personalDetails7_checkBox1)
        checkBoxTwo = view.findViewById(R.id.personalDetails7_checkBox2)
        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 9).apply()


        checkBoxOne.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked && checkBoxTwo.isChecked && isButtonClicked){
                changeButtonBackground()
            }else{
                changeButtonBackgroundGrey()
            }
        }

        checkBoxTwo.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked && checkBoxOne.isChecked && isButtonClicked){
                changeButtonBackground()
            }else{
                changeButtonBackgroundGrey()
            }
        }

        PersonalDetails7ContinueBtn.setOnClickListener {
            val documentfirst = DocumentsFirst()
            userActivity.LoadProgress(documentfirst)
            userActivity.onNextButtonClick(documentfirst)
        }

        initButtons(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonalDetails7.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalDetails7().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun initButtons(view: View) {
        val buttonIds = arrayOf(
            R.id.personalDetails7_upto1,
            R.id.personalDetails7_10to25,
            R.id.personalDetails7_25to50,
            R.id.personalDetails7_1to5,
            R.id.personalDetails7_50to1,
            R.id.personalDetails7_5to10,
            R.id.personalDetails7_moreThan1
        )
        for (id in buttonIds) {
            val button = view.findViewById<Button>(id)
            button.setOnClickListener { onButtonClick(button) }
            buttons.add(button)
        }
    }
    private fun onButtonClick(clickedButton: Button) {
        val drawable1: Drawable = requireContext().getDrawable(R.drawable.buy_sell_background)!!
        val drawable2: Drawable = requireContext().getDrawable(R.drawable.buy_sell_background_grey)!!

        clickedButton.background = drawable1
        isButtonClicked = true
        clickedButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.card_blue))

        for (button in buttons) {
            if (button.id != clickedButton.id) {
                button.background = drawable2
                button.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            }
        }
    }

    fun changeButtonBackground(){
        PersonalDetails7ContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }
    fun changeButtonBackgroundGrey(){
        PersonalDetails7ContinueBtn.setBackgroundColor(resources.getColor(R.color.grey))
    }
}