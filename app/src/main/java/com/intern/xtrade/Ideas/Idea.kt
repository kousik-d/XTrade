package com.intern.xtrade.Ideas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apxor.androidsdk.core.ApxorSDK
import com.google.android.material.tabs.TabLayout
import com.intern.xtrade.Adapters.FragmentPageAdapterIPO
import com.intern.xtrade.Adapters.FragmentPageAdapterIdea
import com.intern.xtrade.Adapters.FragmentPageAdapterOrders
import com.intern.xtrade.R

/**
 * A simple [Fragment] subclass.
 * Use the [Idea.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Idea : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var adapterIdea : FragmentPageAdapterIdea
    lateinit var ideaViewPager : ViewPager2
    lateinit var ideaTabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        ApxorSDK.logAppEvent("Idea_page_launched")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_idea, container, false)

        adapterIdea = FragmentPageAdapterIdea(childFragmentManager,lifecycle)
        ideaViewPager = view.findViewById(R.id.idea_viewPager)
        ideaTabLayout = view.findViewById(R.id.idea_tabLayout)

        ideaViewPager.adapter = adapterIdea
        ideaViewPager.setSaveEnabled(false)
        ideaTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    ideaViewPager.currentItem = it.position;
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        ideaViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                ideaTabLayout.selectTab(ideaTabLayout.getTabAt(position))
            }
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Idea.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Idea().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}