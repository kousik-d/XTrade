package com.intern.xtrade.UserOnBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.intern.xtrade.BackGroundChange.ButtonBackgroundChange
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

    lateinit var PersonalDetails2ContinueBtn : AppCompatButton
    lateinit var EducationHighSchoolbtn : AppCompatButton
    lateinit var EducationGraduateBtn : AppCompatButton
    lateinit var EducationPostGraduateBtn : AppCompatButton
    lateinit var EducationDoctrateBtn : AppCompatButton
    lateinit var EducationProfessionalDegreeBtn : AppCompatButton
    lateinit var EducationUnderHighSchoolBtn : AppCompatButton
    lateinit var EducationIlletrateBtn : AppCompatButton
    lateinit var EducationOthersBtn : AppCompatButton


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
        EducationHighSchoolbtn = view.findViewById(R.id.personalDetails2_highSchool)
        EducationDoctrateBtn = view.findViewById(R.id.personalDetails2_doctorate)
        EducationGraduateBtn = view.findViewById(R.id.personalDetails2_graduate)
        EducationPostGraduateBtn = view.findViewById(R.id.personalDetails2_postGraduate)
        EducationProfessionalDegreeBtn = view.findViewById(R.id.personalDetails2_professionalDegree)
        EducationUnderHighSchoolBtn = view.findViewById(R.id.personalDetails2_underHighSchool)
        EducationIlletrateBtn = view.findViewById(R.id.personalDetails2_illiterate)
        EducationOthersBtn = view.findViewById(R.id.personalDetails2_others)

        EducationDoctrateBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationDoctrateBtn,requireContext())
        }
        EducationUnderHighSchoolBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationUnderHighSchoolBtn,requireContext())
        }
        EducationGraduateBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationGraduateBtn,requireContext())
        }
        EducationOthersBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationOthersBtn,requireContext())
        }
        EducationPostGraduateBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationPostGraduateBtn,requireContext())
        }
        EducationProfessionalDegreeBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationProfessionalDegreeBtn,requireContext())
        }
        EducationIlletrateBtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationIlletrateBtn,requireContext())
        }
        EducationHighSchoolbtn.setOnClickListener {
            ChangeBackGroundForALlButtons()
            ButtonBackgroundChange.ChangeBackgound(EducationHighSchoolbtn,requireContext())
        }


        userActivity = activity as UserDetails
        PersonalDetails2ContinueBtn.setOnClickListener {
            userActivity.onNextButtonClick(PersonalDetails3())
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
    fun ChangeBackGroundForALlButtons(){
        EducationUnderHighSchoolBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationUnderHighSchoolBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationHighSchoolbtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationHighSchoolbtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationIlletrateBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationIlletrateBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationOthersBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationOthersBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationGraduateBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationGraduateBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationProfessionalDegreeBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationProfessionalDegreeBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationPostGraduateBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationPostGraduateBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

        EducationDoctrateBtn.setBackgroundResource(R.drawable.buy_sell_background_grey)
        EducationDoctrateBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
    }


}