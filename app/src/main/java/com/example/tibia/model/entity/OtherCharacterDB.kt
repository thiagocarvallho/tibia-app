package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "other_character",
    indices = [Index(value = ["character_local_id", "name"], unique = true)]
)
data class OtherCharacterDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "position")
    val position: String? = null,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "traded")
    val traded: Boolean? = null,

    @ColumnInfo(name = "world")
    val world: String? = null,

    @ColumnInfo(name = "deleted")
    val deleted: Boolean? = null,

    @ColumnInfo(name = "main")
    val main: Boolean? = null
)