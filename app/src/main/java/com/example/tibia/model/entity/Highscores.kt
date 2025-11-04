package com.example.tibia.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highscores")
data class HighscoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val rank: Int,
    val level: Int,
    val vocation: String,
    val world: String,
    val value: String,
    val category: String,
    val timestamp: Long
)