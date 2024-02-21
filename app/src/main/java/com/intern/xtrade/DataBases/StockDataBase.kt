package com.intern.xtrade.DataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intern.xtrade.Daos.StockDao
import com.intern.xtrade.DataClasses.StockInfo

@Database(entities = [StockInfo::class], version = 1)
abstract class StockDataBase: RoomDatabase() {
    abstract fun getStockDao() : StockDao
    companion object{
        @Volatile
        private var INSTANCE:StockDataBase? = null
        private var LOCK=Any()

        operator fun invoke(context: Context): StockDataBase = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDataBase(context).also {
                INSTANCE = it
            }
        }

        fun createDataBase(context: Context):StockDataBase = Room.databaseBuilder(
            context,
            StockDataBase::class.java,
            "StockDB"
        ).build()
    }
}