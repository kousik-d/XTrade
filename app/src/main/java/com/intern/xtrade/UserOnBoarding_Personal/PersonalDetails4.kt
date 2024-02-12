package com.intern.xtrade.UserOnBoarding_Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import com.intern.xtrade.R
import com.intern.xtrade.UserDetails
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
class PersonalDetails4 : Fragment() {
    private val buttons = mutableListOf<Button>()
    private lateinit var userActivity : UserDetails
    lateinit var PersonalDetails4ContinueBtn : Button
    var isButtonClicked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personal_details4, container, false)
        PersonalDetails4ContinueBtn = view.findViewById(R.id.personalDetails4_continue)
        userActivity = activity as UserDetails
        PersonalDetails4ContinueBtn = view.findViewById(R.id.personalDetails4_continue)
        PersonalDetails4ContinueBtn.setOnClickListener{
            userActivity.onNextButtonClick(PersonalDetails5())
        }
        initButtons(view)
        return view
    }
    private fun initButtons(view: View) {
        val buttonIds = arrayOf(
            R.id.personalDetails4_salary,
            R.id.personalDetails4_businessIncome,
            R.id.personalDetails4_gift,
            R.id.personalDetails4_ancestralProperty,
            R.id.personalDetails4_rentalIncome,
            R.id.personalDetails4_prizeMoney,
            R.id.personalDetails4_royality,
            R.id.personalDetails4_others
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
        PersonalDetails4ContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }
}