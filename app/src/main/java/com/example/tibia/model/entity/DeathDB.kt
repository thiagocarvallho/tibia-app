package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "death",
    indices = [Index(value = ["character_local_id", "time"], unique = true)]
)
data class DeathDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null,

    @ColumnInfo(name = "level")
    val level: Int? = null,

    @ColumnInfo(name = "reason")
    val reason: String? = null,

    @ColumnInfo(name = "time")
    val time: String? = null
)
