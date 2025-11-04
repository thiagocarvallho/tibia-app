package com.example.tibia.model.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "death_killer",
    indices = [Index(value = ["death_local_id", "name"], unique = true)]
)
data class DeathKillerDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null,

    @ColumnInfo(name = "death_local_id")
    val deathLocalId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "player")
    val player: Boolean? = null,

    @ColumnInfo(name = "summon")
    val summon: String? = null,

    @ColumnInfo(name = "traded")
    val traded: Boolean? = null
)