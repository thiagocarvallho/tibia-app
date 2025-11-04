package com.example.tibia.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.tibia.model.entity.DeathAssistDB
import com.example.tibia.model.entity.DeathDB
import com.example.tibia.model.entity.DeathKillerDB
import java.util.Collections.emptyList

data class DeathWithRelations(
    @Embedded val death: DeathDB,

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val assists: List<DeathAssistDB> = emptyList(),

    @Relation(
        parentColumn = "local_id",
        entityColumn = "character_local_id"
    )
    val killers: List<DeathKillerDB> = emptyList()
)