package com.intern.xtrade.DataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IPOData")
data class IPOData(
    @PrimaryKey
    val IPOId : Int,
    val IPOName : String,
    val IPOImage : Int,
    val IPOOpenDate : String,
    val IPOCloseDate : String,
    val IPOMinPrice : String,
    val IPOMaxPrice : String,
    val IPOLotsize : Int,
    var isIPOOpen : Boolean,
    var isApplied: Boolean,
    var isForthComing : Boolean
)