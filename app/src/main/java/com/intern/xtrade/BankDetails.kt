package com.intern.xtrade

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.text.isDigitsOnly
import com.intern.xtrade.UserOnBoarding_Personal.PersonalDetails

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BankDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class BankDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var userActivity: UserDetails
    lateinit var BankContinueBtn : AppCompatButton
    lateinit var BankAccountNumber: EditText
    lateinit var BankIFSCCode : EditText
    var isBankAccountNumberOk = false
    var isBankIFSCCode = false


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
        val view = inflater.inflate(R.layout.fragment_bank_details, container, false)
        BankContinueBtn = view.findViewById(R.id.bankDetails_continue)

        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 2).apply()
        BankAccountNumber = view.findViewById(R.id.bankDetails_accountNumber)
        BankIFSCCode = view.findViewById(R.id.bankDetails_ifscCode)

        BankIFSCCode.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val IFSCCode = s.toString()
                if(IFSCCode.isNotEmpty() && IFSCCode.length==11 && IFSCCode[4]=='0'){
                    isBankIFSCCode = true
                    if(isBankAccountNumberOk){
                        changeButtonBackground()
                    }
                }else{
                    isBankIFSCCode = false
                    changeButtonBackgroundtoGrey()
                }
            }

        })

        BankAccountNumber.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val bankAccountNumber = s.toString()
                if(bankAccountNumber.isDigitsOnly() && bankAccountNumber.length>10){
                    isBankAccountNumberOk = true
                    if(isBankIFSCCode){
                        changeButtonBackground()
                    }
                }else{
                    isBankAccountNumberOk = false
                    changeButtonBackgroundtoGrey()
                }
            }
        })

        BankContinueBtn.setOnClickListener {
            if(isBankAccountNumberOk && isBankIFSCCode){
                val personalDetails = PersonalDetails()
                userActivity.LoadProgress(personalDetails)
                userActivity.onNextButtonClick(personalDetails)
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
         * @return A new instance of fragment BankDetails.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BankDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun changeButtonBackground(){
        BankContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }

    fun changeButtonBackgroundtoGrey(){
        BankContinueBtn.setBackgroundColor(resources.getColor(R.color.grey))
    }



}