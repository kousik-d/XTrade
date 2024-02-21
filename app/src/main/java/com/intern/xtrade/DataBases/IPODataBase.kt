package com.intern.xtrade.DataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intern.xtrade.Daos.IPODao
import com.intern.xtrade.Daos.StockDao
import com.intern.xtrade.DataClasses.IPOData
import com.intern.xtrade.DataClasses.StockInfo

@Database(entities = [IPOData::class], version = 1)
abstract class IPODataBase: RoomDatabase() {
    abstract fun getIPODao() : IPODao
    companion object{
        @Volatile
        private var INSTANCE:IPODataBase? = null
        private var LOCK=Any()

        operator fun invoke(context: Context): IPODataBase = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDataBase(context).also {
                INSTANCE = it
            }
        }

        fun createDataBase(context: Context):IPODataBase = Room.databaseBuilder(
            context,
            IPODataBase::class.java,
            "IpoDB"
        ).build()
    }
}