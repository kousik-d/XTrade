package com.intern.xtrade.Repositories

import androidx.lifecycle.LiveData
import com.intern.xtrade.DataBases.StockDataBase
import com.intern.xtrade.DataClasses.StockInfo
import kotlinx.coroutines.flow.Flow

class StockRepository(private val stockDataBase: StockDataBase) {
    var allStocks : LiveData<MutableList<StockInfo>> = stockDataBase.getStockDao().getAllStocks()

    var allStockHoldings : Flow<List<StockInfo>> = stockDataBase.getStockDao().getAllStockHoldings()

    var stockWatchlist: Flow<List<StockInfo>> = stockDataBase.getStockDao().getAllStockWatchlist()

    var stockOrdersOpen : Flow<MutableList<StockInfo>> = stockDataBase.getStockDao().getAllStockOrdersOpen()

    var stockOrdersExecuted : Flow<MutableList<StockInfo>> = stockDataBase.getStockDao().getAllStockOrdersExecuted()

    suspend fun insertStock(stockInfo: StockInfo) = stockDataBase.getStockDao().insertStock(stockInfo)

    suspend fun updateStock(stockInfo: StockInfo) = stockDataBase.getStockDao().updateStock(stockInfo)
}