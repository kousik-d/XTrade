package com.intern.xtrade.UserOnBoarding

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.intern.xtrade.R
import com.intern.xtrade.UserDetails
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PANDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class PANDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var userActivity: UserDetails
    private lateinit var PanNumber: EditText
    lateinit var sharedPreferences: SharedPreferences

    lateinit var PanContinueBtn : Button
    lateinit var PanDetailsDob : Button
    lateinit var DataPickDialog : DatePickerDialog
    lateinit var PanNumberWarning : TextView
    var isButtonClickable = false
    var isDateSelected = false

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
        userActivity = activity as UserDetails
        val view = inflater.inflate(R.layout.fragment_p_a_n_details, container, false)
        PanContinueBtn = view.findViewById(R.id.panDetails_continue)
        PanNumber = view.findViewById(R.id.panDetails_panNumber)
        PanNumberWarning = view.findViewById(R.id.pan_PanNumberWarning)

        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 1).apply()
        PanNumber.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val panNumber = s.toString()
                if(panNumber.length<10){
                    PanNumberWarning.text = "Enter a valid PAN"
                    PanNumberWarning.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.red
                    ))
                }else if(panNumber.length==10){
                    PanNumberWarning.text = "PAN is valid"
                    isButtonClickable = true
                    if(isDateSelected==true){
                        PanContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
                    }
                    PanNumberWarning.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.green
                    ))
                }else{
                    isButtonClickable = false
                    PanNumberWarning.text = "Enter a valid PAN"
                    PanNumberWarning.setTextColor(ContextCompat.getColor(requireContext(),
                        R.color.red
                    ))
                }
            }

        })
        initDatePicker()
        PanDetailsDob = view.findViewById(R.id.panDetails_dob)
        PanDetailsDob.text = getTodaysDate()
        PanDetailsDob.setOnClickListener{
            openDatePicker()
        }

            PanContinueBtn.setOnClickListener {
                if(isButtonClickable && isDateSelected) {
                    val bankDetails = BankDetails()
                    userActivity.LoadProgress(bankDetails)
                    userActivity.onNextButtonClick(bankDetails)
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
         * @return A new instance of fragment PANDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PANDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun getTodaysDate(): String {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
            val formattedDate = makeDateString(day, month + 1, year)
            isDateSelected = true
            if(isButtonClickable==true){
                PanContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
            }
            PanDetailsDob.text = formattedDate
        }

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        DataPickDialog = DatePickerDialog(requireContext(),
            R.style.DatePickerDialogStyle, dateSetListener, year, month, day)
        DataPickDialog.datePicker.maxDate = System.currentTimeMillis()

    }
    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return "${getMonthFormat(month)} $day $year"
    }

    private fun getMonthFormat(month: Int): String {
        return when (month) {
            1 -> "JAN"
            2 -> "FEB"
            3 -> "MAR"
            4 -> "APR"
            5 -> "MAY"
            6 -> "JUN"
            7 -> "JUL"
            8 -> "AUG"
            9 -> "SEP"
            10 -> "OCT"
            11 -> "NOV"
            12 -> "DEC"
            else -> "JAN"
        }
    }

    private fun openDatePicker() {
        DataPickDialog.show()
    }
}