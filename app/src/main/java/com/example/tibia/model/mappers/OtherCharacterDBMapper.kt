package com.example.tibia.model.mappers

import com.example.tibia.model.domain.OtherCharacterDomain
import com.example.tibia.model.entity.OtherCharacterDB
import com.example.tibia.model.serealizer.OtherCharacter

class OtherCharacterDBMapper : EntityMapper<OtherCharacterDB, OtherCharacterDomain>() {
    override fun mapFromEntity(entity: OtherCharacterDB): OtherCharacterDomain {
        return OtherCharacterDomain(
            name = entity.name,
            position = entity.position,
            status = entity.status,
            traded = entity.traded,
            world = entity.world,
            deleted = entity.deleted,
            main = entity.main
        )
    }

    override fun mapToEntity(domainModel: OtherCharacterDomain): OtherCharacterDB {
        return OtherCharacterDB(
            characterLocalId = null,
            name = domainModel.name,
            position = domainModel.position,
            status = domainModel.status,
            traded = domainModel.traded,
            world = domainModel.world,
            deleted = domainModel.deleted,
            main = domainModel.main
        )
    }
}