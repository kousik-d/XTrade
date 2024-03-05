package com.intern.xtrade.UserOnBoarding_Personal

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.apxor.androidsdk.core.ApxorSDK
import com.intern.xtrade.R
import com.intern.xtrade.UserDetails

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetails2.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PersonalDetails2 : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var userActivity : UserDetails

    private val buttons = mutableListOf<Button>()

    lateinit var PersonalDetails2ContinueBtn : AppCompatButton
    var isButtonClicked = false

    lateinit var sharedPreferences : SharedPreferences
    override fun onResume() {
        super.onResume()
        ApxorSDK.trackScreen("Personal_details2")
    }


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
        val view =  inflater.inflate(R.layout.fragment_personal_details2, container, false)

        PersonalDetails2ContinueBtn = view.findViewById(R.id.personalDetails2_continue)

        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 4).apply()

        userActivity = activity as UserDetails
        PersonalDetails2ContinueBtn.setOnClickListener {
            if(isButtonClicked) {
                userActivity.onNextButtonClick(PersonalDetails3())
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
         * @return A new instance of fragment PersonalDetails2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalDetails2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun initButtons(view: View) {
        val buttonIds = arrayOf(
            R.id.personalDetails2_highSchool,
            R.id.personalDetails2_underHighSchool,
            R.id.personalDetails2_postGraduate,
            R.id.personalDetails2_doctorate,
            R.id.personalDetails2_graduate,
            R.id.personalDetails2_professionalDegree,
            R.id.personalDetails2_underHighSchool,
            R.id.personalDetails2_illiterate,
            R.id.personalDetails2_others,
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

        isButtonClicked = true
        changeButtonBackground()
        clickedButton.background = drawable1
        clickedButton.setTextColor(ContextCompat.getColor(requireContext(),R.color.card_blue))

        for (button in buttons) {
            if (button.id != clickedButton.id) {
                button.background = drawable2
                button.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            }
        }
    }
    fun changeButtonBackground(){
        PersonalDetails2ContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }


}