package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tibia.model.entity.HouseDB

@Dao
interface HouseDao {
    @Query("SELECT * FROM house ORDER BY local_id DESC")
    suspend fun get(): List<HouseDB>?

    @Query("SELECT * FROM house")
    suspend fun getAll(): List<HouseDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(house: HouseDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(houses: List<HouseDB>)
}