package com.example.tibia.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tibia.model.CharacterWithRelations
import com.example.tibia.model.DeathWithRelations
import com.example.tibia.model.entity.CharacterDB

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characterDB WHERE is_delete IS NOT 1 ORDER BY local_id DESC")
    suspend fun get(): List<CharacterWithRelations>?

    @Query("SELECT * FROM characterDB")
    suspend fun getAll(): List<CharacterDB>?


    @Query("SELECT * FROM characterDB where name = :name")
    suspend fun getByName(name: String?): CharacterDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterDB>)

    @Transaction
    @Query("SELECT * FROM death WHERE character_local_id = :characterId")
    fun getDeathsWithRelations(characterId: Long): List<DeathWithRelations>
}