package com.intern.xtrade.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.airbnb.lottie.L
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.DataClasses.StockInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface StockDao {
    @Insert()
    suspend fun insertStock(stock : StockInfo)

    @Query("SELECT * FROM StockInfo")
    fun getAllStocks():LiveData<MutableList<StockInfo>>

    @Query("SELECT * FROM StockInfo s WHERE s.isInHoldings = True")
    fun getAllStockHoldings():Flow<List<StockInfo>>

    @Query("SELECT * FROM StockInfo s WHERE s.isInWatchList = True")
    fun getAllStockWatchlist():Flow<List<StockInfo>>

    @Query("SELECT * FROM StockInfo s WHERE s.isInOrders = 1 ")
    fun getAllStockOrdersOpen():Flow<MutableList<StockInfo>>

    @Query("SELECT * FROM StockInfo s WHERE s.isInOrders = 2 ")
    fun getAllStockOrdersExecuted():Flow<MutableList<StockInfo>>

    @Update
    fun updateStock(stock: StockInfo)

}