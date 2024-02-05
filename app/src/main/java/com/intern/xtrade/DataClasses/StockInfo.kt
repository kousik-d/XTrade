package com.intern.xtrade.DataClasses

import android.graphics.drawable.Drawable

data class StockInfo(
    val CompanyName : String,
    val CompanyLogo : Int,
    val StockName: String,
    val GraphBoolean : Boolean,
    var StockPrice: Double,
    var StockPercentage: Double,
    val StockId: Int,
)
