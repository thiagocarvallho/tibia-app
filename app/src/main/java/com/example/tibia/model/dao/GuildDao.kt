package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tibia.model.entity.GuildDB

@Dao
interface GuildDao {
    @Query("SELECT * FROM guild ORDER BY local_id DESC")
    suspend fun get(): List<GuildDB>?

    @Query("SELECT * FROM guild")
    suspend fun getAll(): List<GuildDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(guild: GuildDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(guilds: List<GuildDB>)
}