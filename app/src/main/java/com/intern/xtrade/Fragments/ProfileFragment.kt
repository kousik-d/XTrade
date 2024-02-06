package com.intern.xtrade.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.intern.xtrade.ProfileActivites.Notifications
import com.intern.xtrade.R
import com.intern.xtrade.ProfileActivites.SecurityInformation
import com.intern.xtrade.YourStocks
import com.intern.xtrade.ProfileActivites.YourWishlist

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var showStockBtn : LinearLayout
    lateinit var showWishlist: LinearLayout
    lateinit var showNotificationBtn : LinearLayout
    lateinit var showSecurityBtn : LinearLayout
    lateinit var showHelpandSupportBtn : LinearLayout
    lateinit var showTermAndConditionBtn : LinearLayout

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
        val view = inflater.inflate(R.layout.activity_profile_list, container, false)

        showStockBtn = view.findViewById(R.id.yourStocksOpenBtn)
        showWishlist = view.findViewById(R.id.yourWishlistOpenBtn)
        showNotificationBtn = view.findViewById(R.id.yourNotificationsOpenBtn)
        showSecurityBtn = view.findViewById(R.id.yourSecurityOpenBtn)
        showHelpandSupportBtn = view.findViewById(R.id.yourHelpandSupportOpenBtn)
        showTermAndConditionBtn = view.findViewById(R.id.yourTermsAndConditionOpenBtn)

        showStockBtn.setOnClickListener {
            startActivity(Intent(requireContext(),YourStocks::class.java))
        }
        showWishlist.setOnClickListener {
            startActivity(Intent(requireContext(), YourWishlist::class.java))
        }
        showNotificationBtn.setOnClickListener {
            startActivity(Intent(requireContext(), Notifications::class.java))
        }
        showSecurityBtn.setOnClickListener {
            startActivity(Intent(requireContext(), SecurityInformation::class.java))
        }
        showHelpandSupportBtn.setOnClickListener {
            //Do Something
        }
        showTermAndConditionBtn.setOnClickListener {
            //Do Something
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}