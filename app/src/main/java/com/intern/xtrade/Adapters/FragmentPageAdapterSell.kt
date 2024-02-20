package com.intern.xtrade.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.intern.xtrade.RegularAndAMO.BuyAMO
import com.intern.xtrade.RegularAndAMO.BuyRegularActivity
import com.intern.xtrade.RegularAndAMO.sellRegular

class FragmentPageAdapterSell(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            return sellRegular()
        else
            return sellRegular()
    }
}