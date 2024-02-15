package com.intern.xtrade.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.intern.xtrade.IPO.ForthComing
import com.intern.xtrade.IPO.LiveIPOS
import com.intern.xtrade.IPO.PastBids

class FragmentPageAdapterIPO(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
       if(position==0) {
           return LiveIPOS()
       }
       if(position ==1){
           return ForthComing()
       }
        return PastBids()
    }
}