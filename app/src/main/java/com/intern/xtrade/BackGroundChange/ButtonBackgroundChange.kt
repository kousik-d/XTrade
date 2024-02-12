package com.intern.xtrade.BackGroundChange

import android.content.Context
import android.widget.Button
import androidx.core.content.ContextCompat
import com.intern.xtrade.R

class ButtonBackgroundChange {
    companion object{
        fun ChangeBackgound(SelectedBtn : Button,context : Context){
            SelectedBtn.setBackgroundResource(R.drawable.buy_sell_background)
            SelectedBtn.setTextColor(ContextCompat.getColor(context, R.color.card_blue))
        }
    }
}