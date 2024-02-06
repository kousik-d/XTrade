package com.intern.xtrade.DataClasses


data class StockInfo(
    val StockId: Int,
    val CompanyName : String,
    val CompanyLogo : Int,
    val StockName: String,
    val GraphBoolean : Boolean,
    var StockPrice: Double,
    var StockPercentage: Double,
)
