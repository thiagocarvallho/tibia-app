package com.example.tibia.model.mappers

import com.example.tibia.model.domain.KillerOrAssistDomain
import com.example.tibia.model.entity.DeathKillerDB
import com.example.tibia.model.serealizer.KillerOrAssist

class DeathKillerDBMapper : EntityMapper<DeathKillerDB, KillerOrAssistDomain>() {
    override fun mapFromEntity(entity: DeathKillerDB): KillerOrAssistDomain {
        return KillerOrAssistDomain(
            name = entity.name,
            player = entity.player,
            summon = entity.summon,
            traded = entity.traded
        )
    }

    override fun mapToEntity(domainModel: KillerOrAssistDomain): DeathKillerDB {
        return DeathKillerDB(
            deathLocalId = null,
            name = domainModel.name,
            player = domainModel.player,
            summon = domainModel.summon,
            traded = domainModel.traded
        )
    }
}