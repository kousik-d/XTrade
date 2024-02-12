package com.intern.xtrade

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

        BankAccountNumber = view.findViewById(R.id.bankDetails_accountNumber)
        BankIFSCCode = view.findViewById(R.id.bankDetails_ifscCode)

        BankAccountNumber.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val bankAccountNumber = s.toString()
                if(bankAccountNumber.isDigitsOnly() && bankAccountNumber.length>10){
                    isBankAccountNumberOk = true
                    changeButtonBackground()
                }else{
                    changeButtonBackgroundtoGrey()
                }
            }
        })

        BankContinueBtn.setOnClickListener {
            if(isBankAccountNumberOk){
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
        // TODO: Rename and change types and number of parameters
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
        isBankAccountNumberOk = true
        BankContinueBtn.setBackgroundColor(resources.getColor(R.color.card_blue))
    }

    fun changeButtonBackgroundtoGrey(){
        isBankAccountNumberOk =false
        BankContinueBtn.setBackgroundColor(resources.getColor(R.color.grey))
    }
}