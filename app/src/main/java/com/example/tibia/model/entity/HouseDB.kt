package com.example.tibia.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "house",
    indices = [Index(value = ["character_local_id", "houseid"], unique = true)]
)
data class HouseDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "character_local_id")
    val characterLocalId: Int? = null,

    @ColumnInfo(name = "houseid")
    val houseId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "paid")
    val paid: String? = null,

    @ColumnInfo(name = "town")
    val town: String? = null
)