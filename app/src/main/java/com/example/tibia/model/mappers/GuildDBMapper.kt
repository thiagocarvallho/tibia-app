package com.example.tibia.model.mappers

import com.example.tibia.model.domain.GuildDomain
import com.example.tibia.model.entity.GuildDB
import com.example.tibia.model.remote.Guild

class GuildDBMapper : EntityMapper<GuildDB, GuildDomain>() {
    override fun mapFromEntity(entity: GuildDB): GuildDomain {
        return GuildDomain(
            name = entity.name,
            rank = entity.rank
        )
    }

    override fun mapToEntity(domainModel: GuildDomain): GuildDB {
        return GuildDB(
            characterLocalId = null,
            name = domainModel.name,
            rank = domainModel.rank
        )
    }
}