package com.intern.xtrade.Adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.intern.xtrade.Ideas.IdeaCommodity
import com.intern.xtrade.Ideas.IdeaCurrency
import com.intern.xtrade.Ideas.IdeaEquity
import com.intern.xtrade.Ideas.IdeaFnO
import com.intern.xtrade.RegularAndAMO.BuyAMO
import com.intern.xtrade.RegularAndAMO.BuyRegularActivity

class FragmentPageAdapterIdea(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        if (position == 0)
            return IdeaEquity()
        else if(position == 1)
            return IdeaFnO()
        else if(position == 2)
            return IdeaCurrency()
        else
            return IdeaCommodity()
    }
}