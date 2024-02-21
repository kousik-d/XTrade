package com.intern.xtrade.Repositories

import androidx.lifecycle.asLiveData
import com.intern.xtrade.DataBases.IPODataBase
import com.intern.xtrade.DataClasses.IPOData
import kotlinx.coroutines.flow.Flow

class IPORepository(private val ipoDataBase: IPODataBase) {
    var allIPOs: Flow<MutableList<IPOData>> = ipoDataBase.getIPODao().getIPOs()

    val allLiveIPO = allIPOs.asLiveData()
    suspend fun insertExpense(ipo : IPOData) = ipoDataBase.getIPODao().insertIPO(ipo)

}