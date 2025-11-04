package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tibia.model.entity.AchievementDB

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievement ORDER BY local_id DESC")
    suspend fun get(): List<AchievementDB>?

    @Query("SELECT * FROM achievement")
    suspend fun getAll(): List<AchievementDB>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(achievement: AchievementDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(achievements: List<AchievementDB>)
}