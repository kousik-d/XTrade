package com.intern.xtrade.UserOnBoarding_Personal

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.intern.xtrade.R
import com.intern.xtrade.UserDetails

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetails5.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PersonalDetails5 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val buttons = mutableListOf<Button>()
    private lateinit var userActivity : UserDetails
    lateinit var PersonalDetails5ContinueBtn : Button
    var isButtonClicked = false

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
        val view = inflater.inflate(R.layout.fragment_personal_details5, container, false)
        userActivity = activity as UserDetails
        PersonalDetails5ContinueBtn = view.findViewById(R.id.personalDetails5_continue)
        PersonalDetails5ContinueBtn.setOnClickListener{
            if(isButtonClicked) {
                userActivity.onNextButtonClick(PersonalDetails6())
            }
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
         * @return A new instance of fragment PersonalDetails5.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalDetails5().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun initButtons(view: View) {
        val buttonIds = arrayOf(
            R.id.personalDetails5_10to25,
            R.id.personalDetails5_1to5,
            R.id.personalDetails5_25to50,
            R.id.personalDetails5_5to10,
            R.id.personalDetails5_50to1,
            R.id.personalDetails5_moreThan1,
            R.id.personalDetails5_upto1,
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
        changeButtonBackground()
        clickedButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.card_blue))

        for (button in buttons) {
            if (button.id != clickedButton.id) {
                button.background = drawable2
                button.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            }
        }
    }

    fun changeButtonBackground(){
        isButtonClicked = true
        PersonalDetails5ContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }
}