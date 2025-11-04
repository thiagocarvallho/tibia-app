package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



@Entity(
    tableName = "achievement",
    indices = [Index(
        value = ["character_local_id", "name"],
        unique = true
    )]
)
data class AchievementDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null, // FK para CharacterDB

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "grade")
    val grade: Int? = null,

    @ColumnInfo(name = "secret")
    val secret: Boolean? = null
)
