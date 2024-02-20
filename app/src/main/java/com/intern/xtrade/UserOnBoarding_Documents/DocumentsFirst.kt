package com.intern.xtrade.UserOnBoarding_Documents

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.intern.xtrade.R
import com.intern.xtrade.UserDetails

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DocumentsFirst.newInstance] factory method to
 * create an instance of this fragment.
 */
class DocumentsFirst : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var DocumentFirstContinue : AppCompatButton
    lateinit var DocumentFirstPhoto : TextView
    lateinit var DocumentsFirstSign : TextView
    private lateinit var userActivity : UserDetails
    var isPhotoClicked = false
    var isDocumentsSignClicked = false
    lateinit var sharedPreferences: SharedPreferences
    lateinit var warning : TextView

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
        val view = inflater.inflate(R.layout.fragment_documents_first, container, false)
        DocumentFirstContinue = view.findViewById(R.id.documents1_continue)
        DocumentFirstPhoto = view.findViewById(R.id.documents1_photo)
        DocumentsFirstSign = view.findViewById(R.id.documents1_sign)

        sharedPreferences = requireActivity().getSharedPreferences("APP_STATUS", Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("current_step", 10).apply()


        warning = view.findViewById(R.id.textWarning)



        DocumentFirstPhoto.setOnClickListener{
            warning.text = "Photo uploaded successfully !"
            Log.i("DOCUMENTS","CLICLED ${warning.text}")
            warning.setTextColor(resources.getColor(R.color.green))
            isPhotoClicked = true
            if(isPhotoClicked && isDocumentsSignClicked) {
                changeButtonBackground()
            }
        }

        DocumentsFirstSign.setOnClickListener{
            isDocumentsSignClicked = true
            warning.text = "Signature uploaded successfully !"
            warning.setTextColor(resources.getColor(R.color.green))

            if(isPhotoClicked && isDocumentsSignClicked) {
                changeButtonBackground()
            }
        }

        userActivity = activity as UserDetails
        DocumentFirstContinue.setOnClickListener {
            if(isPhotoClicked && isDocumentsSignClicked==false)
            {
                warning.text = "*Please upload your Signature"
                warning.setTextColor(resources.getColor(R.color.red))
            }
            else if(isPhotoClicked==false && isDocumentsSignClicked)
            {
                warning.text = "*Please upload your Photo"
                warning.setTextColor(resources.getColor(R.color.red))
            }
            if(isPhotoClicked && isDocumentsSignClicked) {
                userActivity.onNextButtonClick(DocumentsSecond())
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
         * @return A new instance of fragment DocumentsFirst.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DocumentsFirst().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun changeButtonBackground(){
        DocumentFirstContinue.setBackgroundColor(resources.getColor(R.color.card_blue))
    }

}