package com.example.tibia.model.domain

import java.util.Collections.*

data class CharacterDomain(
    val name: String?,
    val vocation: String?,
    val level: Int?,
    val sex: String?,
    val world: String?,
    val residence: String?,
    val marriedTo: String?,
    val accountStatus: String?,
    val lastLogin: String?,
    val accountBadges: List<AccountBadgeDomain> = emptyList(),
    val achievements: List<AchievementDomain> = emptyList(),
    val guild: GuildDomain? = null,
    val houses: List<HouseDomain> = emptyList(),
    val deaths: List<DeathDomain> = emptyList(),
    val otherCharacters: List<OtherCharacterDomain> = emptyList()
)