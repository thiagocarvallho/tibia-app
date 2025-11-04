package com.example.tibia.model.mappers

import com.example.tibia.model.domain.DeathDomain
import com.example.tibia.model.entity.DeathDB
import com.example.tibia.model.serealizer.Death
import java.util.Collections.emptyList

class DeathDBMapper : EntityMapper<DeathDB, DeathDomain>() {
    override fun mapFromEntity(entity: DeathDB): DeathDomain {
        return DeathDomain(
            level = entity.level,
            reason = entity.reason,
            time = entity.time,
            assists = emptyList(), // vai mapear depois com DeathAssistDBMapper
            killers = emptyList()  // vai mapear depois com DeathKillerDBMapper
        )
    }

    override fun mapToEntity(domainModel: DeathDomain): DeathDB {
        return DeathDB(
            characterLocalId = null,
            level = domainModel.level,
            reason = domainModel.reason,
            time = domainModel.time
        )
    }
}