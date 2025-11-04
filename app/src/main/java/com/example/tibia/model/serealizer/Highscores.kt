package com.example.tibia.model.entity

data class HighscoresResponse(
    val highscores: HighscoresData
)

data class HighscoresData(
    val world: String,
    val category: String,
    val vocation: String,
    val highscore_list: List<HighscoreItem>
)

data class HighscoreItem(
    val rank: Int,
    val name: String,
    val vocation: String,
    val world: String,
    val level: Int,
    val value: String
)
