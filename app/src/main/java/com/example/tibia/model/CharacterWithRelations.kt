package com.example.tibia.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tibia.model.entity.*
import java.util.Collections.emptyList

data class CharacterWithRelations(
    @Embedded val character: CharacterDB,

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val badges: List<AccountBadgeDB> = emptyList(),

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val achievements: List<AchievementDB> = emptyList(),

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val guilds: GuildDB? = null,

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val houses: List<HouseDB> = emptyList(),

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id",
        entity = DeathDB::class
    )
    val deaths: List<DeathWithRelations> = emptyList(),

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val otherCharacters: List<OtherCharacterDB> = emptyList()
)