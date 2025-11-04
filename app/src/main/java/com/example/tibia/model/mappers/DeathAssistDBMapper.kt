package com.example.tibia.model.mappers

import com.example.tibia.model.domain.KillerOrAssistDomain
import com.example.tibia.model.entity.DeathAssistDB
import com.example.tibia.model.serealizer.KillerOrAssist

class DeathAssistDBMapper : EntityMapper<DeathAssistDB, KillerOrAssistDomain>() {
    override fun mapFromEntity(entity: DeathAssistDB): KillerOrAssistDomain {
        return KillerOrAssistDomain(
            name = entity.name,
            player = entity.player,
            summon = entity.summon,
            traded = entity.traded
        )
    }

    override fun mapToEntity(domainModel: KillerOrAssistDomain): DeathAssistDB {
        return DeathAssistDB(
            deathLocalId = null,
            name = domainModel.name,
            player = domainModel.player,
            summon = domainModel.summon,
            traded = domainModel.traded
        )
    }
}