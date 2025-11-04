package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "account_badge",
    indices = [Index(
        value = ["character_local_id", "name"],
        unique = true
    )]
)
data class AccountBadgeDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null, // FK para CharacterDB

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "icon_url")
    val iconUrl: String? = null
)
