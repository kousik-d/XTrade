package com.intern.xtrade.DataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "StockInfo")
data class StockInfo(
    @PrimaryKey
    val StockId: Int,
    val CompanyName : String,
    val CompanyLogo : Int,
    val StockName: String,
    val GraphBoolean : Boolean,
    var StockPrice: Double,
    var StockPercentage: Double,
    var isInWatchList : Boolean,
    var isInHoldings : Boolean,
    var isInOrders : Int,
)
