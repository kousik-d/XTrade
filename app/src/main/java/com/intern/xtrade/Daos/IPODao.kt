package com.intern.xtrade.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.intern.xtrade.DataClasses.IPOData
import kotlinx.coroutines.flow.Flow

@Dao
interface IPODao {
    @Insert
    suspend fun insertIPO(ipoData : IPOData)

    @Query("SELECT * FROM IPOData")
    fun getIPOs(): Flow<List<IPOData>>


}