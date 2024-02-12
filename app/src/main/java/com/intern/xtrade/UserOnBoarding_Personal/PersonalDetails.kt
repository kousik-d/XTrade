package com.intern.xtrade.UserOnBoarding_Personal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.intern.xtrade.BackGroundChange.ButtonBackgroundChange

import com.intern.xtrade.R
import com.intern.xtrade.UserDetails
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetails.newInstance] factory method to
 * create an instance of this fragment.
 */

class PersonalDetails : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var userActivity : UserDetails

    var MartialStatusSelected = false
    var GenderSelected = false

    lateinit var PersonalDetailsContinueBtn : AppCompatButton
    lateinit var MaritalStatusSingle : AppCompatButton
    lateinit var MaritalStatusMarried : AppCompatButton
    lateinit var GenderMale : AppCompatButton
    lateinit var GenderFemale : AppCompatButton
    lateinit var GenderOthers : AppCompatButton
    lateinit var FatherName: EditText

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
        val view = inflater.inflate(R.layout.fragment_personal_details, container, false)
        PersonalDetailsContinueBtn = view.findViewById(R.id.personalDetails_continue)

        MaritalStatusSingle = view.findViewById(R.id.personalDetails_single)
        MaritalStatusMarried = view.findViewById(R.id.personalDetails_married)
        GenderMale = view.findViewById(R.id.personalDetails_male)
        GenderFemale = view.findViewById(R.id.personalDetails_female)
        GenderOthers = view.findViewById(R.id.personalDetails_others)
        FatherName = view.findViewById(R.id.personalDetails_fatherName)


        //Need to do some fixes
        FatherName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val FatherName = s.toString()
                if(FatherName.isNotEmpty()){
                    if(MartialStatusSelected && GenderSelected){
                        changeButtonBackground()
                    }
                }
            }
        })


        MaritalStatusSingle.setOnClickListener(this)
        MaritalStatusMarried.setOnClickListener(this)
        GenderMale.setOnClickListener(this)
        GenderFemale.setOnClickListener(this)
        GenderOthers.setOnClickListener(this)




        userActivity = activity as UserDetails
        PersonalDetailsContinueBtn.setOnClickListener {
            if(MartialStatusSelected && GenderSelected){
                userActivity.onNextButtonClick(PersonalDetails2())
            }
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
         * @return A new instance of fragment PersonalDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.personalDetails_single ->{
                resetBackgroundForMartialStatus()
                MartialStatusSelected = true
//                changeButtonBackground()
                ButtonBackgroundChange.ChangeBackgound(MaritalStatusSingle,requireContext())
            }
            R.id.personalDetails_married ->{
                resetBackgroundForMartialStatus()
                MartialStatusSelected = true
//                changeButtonBackground()
                ButtonBackgroundChange.ChangeBackgound(MaritalStatusMarried,requireContext())
            }
            R.id.personalDetails_male ->{
                ChangeBackGroundForALlButtons()
                GenderSelected = true
                ButtonBackgroundChange.ChangeBackgound(GenderMale,requireContext())
            }
            R.id.personalDetails_female ->{
                ChangeBackGroundForALlButtons()
                GenderSelected = true
                ButtonBackgroundChange.ChangeBackgound(GenderFemale,requireContext())
            }
            R.id.personalDetails_others ->{
                ChangeBackGroundForALlButtons()
                GenderSelected = true
                ButtonBackgroundChange.ChangeBackgound(GenderOthers,requireContext())
            }
        }
    }

    fun resetBackgroundForMartialStatus(){
        MaritalStatusSingle.setBackgroundResource(R.drawable.buy_sell_background_grey)
        MaritalStatusSingle.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
        MaritalStatusMarried.setBackgroundResource(R.drawable.buy_sell_background_grey)
        MaritalStatusMarried.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
    }

    fun ChangeBackGroundForALlButtons(){
        GenderMale.setBackgroundResource(R.drawable.buy_sell_background_grey)
        GenderMale.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
        GenderFemale.setBackgroundResource(R.drawable.buy_sell_background_grey)
        GenderFemale.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
        GenderOthers.setBackgroundResource(R.drawable.buy_sell_background_grey)
        GenderOthers.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
    }

    fun changeButtonBackgroundtoGrey(){
        PersonalDetailsContinueBtn.setBackgroundColor(resources.getColor(R.color.grey))
    }


    fun changeButtonBackground(){
        PersonalDetailsContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }
}