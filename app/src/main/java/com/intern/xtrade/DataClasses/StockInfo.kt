package com.intern.xtrade.DataClasses

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.intern.xtrade.YourWishlist


data class StockInfo(
    val StockId: Int,
    val CompanyName : String,
    val CompanyLogo : Int,
    val StockName: String,
    val GraphBoolean : Boolean,
    var StockPrice: Double,
    var StockPercentage: Double,
)
