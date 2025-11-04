package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "guild",
    indices = [Index(value = ["character_local_id"], unique = true)]
)
data class GuildDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "rank")
    val rank: String? = null
)