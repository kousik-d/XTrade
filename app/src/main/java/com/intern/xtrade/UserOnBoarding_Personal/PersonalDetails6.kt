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
    private val buttons = mutableListOf<Button>()
    lateinit var PersonalDetails6ContinueBtn : Button
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
        val view = inflater.inflate(R.layout.fragment_personal_details6, container, false)
        userActivity = activity as UserDetails
        PersonalDetails6ContinueBtn = view.findViewById(R.id.personalDetails6_continue)
        PersonalDetails6ContinueBtn.setOnClickListener{
            if(isButtonClicked) {
                userActivity.onNextButtonClick(PersonalDetails7())
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
    private fun initButtons(view: View) {
        val buttonIds = arrayOf(
            R.id.personalDetails6_1exp,
            R.id.personalDetails6_2exp,
            R.id.personalDetails6_3exp,
            R.id.personalDetails6_4exp,
            R.id.personalDetails6_5exp,
            R.id.personalDetails6_moreThan5,
            R.id.personalDetails6_noexp,
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
        PersonalDetails6ContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }
}