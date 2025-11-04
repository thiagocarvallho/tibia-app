package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tibia.model.entity.HighscoreEntity

@Dao
interface HighscoreDao {
    @Query("SELECT * FROM highscores WHERE world = :world AND category = :category ORDER BY rank ASC")
    suspend fun getAll(world: String, category: String): List<HighscoreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<HighscoreEntity>)

    @Query("DELETE FROM highscores WHERE world = :world AND category = :category")
    suspend fun clear(world: String, category: String)

    @Query("""
        SELECT * FROM highscores
        WHERE world = :world AND category = :category
        ORDER BY rank ASC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getHighscoresPaged(
        world: String,
        category: String,
        offset: Int,
        limit: Int
    ): List<HighscoreEntity>


    @Query("""
        SELECT * FROM highscores
        WHERE world = :world
          AND category = :category
          AND rank > :lastRank
        ORDER BY rank ASC
        LIMIT :pageSize
    """)
    suspend fun getHighscoresAfterRank(
        world: String,
        category: String,
        lastRank: Int,
        pageSize: Int
    ): List<HighscoreEntity>
}
