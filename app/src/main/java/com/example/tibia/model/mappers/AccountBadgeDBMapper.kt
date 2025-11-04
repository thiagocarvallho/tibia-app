package com.example.tibia.model.mappers

import com.example.tibia.model.domain.AccountBadgeDomain
import com.example.tibia.model.entity.AccountBadgeDB
import com.example.tibia.model.remote.AccountBadge

class AccountBadgeDBMapper : EntityMapper<AccountBadgeDB, AccountBadgeDomain>() {
    override fun mapFromEntity(entity: AccountBadgeDB): AccountBadgeDomain {
        return AccountBadgeDomain(
            name = entity.name,
            description = entity.description,
            iconUrl = entity.iconUrl
        )
    }

    override fun mapToEntity(domainModel: AccountBadgeDomain): AccountBadgeDB {
        return AccountBadgeDB(
            characterLocalId = null, // definir no insert, ao salvar no Character
            name = domainModel.name,
            description = domainModel.description,
            iconUrl = domainModel.iconUrl
        )
    }
}