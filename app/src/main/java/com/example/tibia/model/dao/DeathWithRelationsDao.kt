package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tibia.model.DeathWithRelations
import com.example.tibia.model.entity.DeathAssistDB
import com.example.tibia.model.entity.DeathDB
import com.example.tibia.model.entity.DeathKillerDB

@Dao
interface DeathWithRelationsDao {
    @Transaction
    @Query("SELECT * FROM death ORDER BY local_id DESC")
    suspend fun get(): List<DeathWithRelations>?

    @Transaction
    @Query("SELECT * FROM death")
    suspend fun getAll(): List<DeathWithRelations>?



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(death: DeathDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(deaths: List<DeathDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssists(assists: List<DeathAssistDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKillers(killers: List<DeathKillerDB>)


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(death: DeathWithRelations)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(deaths: List<DeathWithRelations>)

}