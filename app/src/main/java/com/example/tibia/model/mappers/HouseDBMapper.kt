package com.example.tibia.model.mappers

import com.example.tibia.model.domain.HouseDomain
import com.example.tibia.model.entity.HouseDB
import com.example.tibia.model.serealizer.House

class HouseDBMapper : EntityMapper<HouseDB, HouseDomain>() {
    override fun mapFromEntity(entity: HouseDB): HouseDomain {
        return HouseDomain(
            houseId = entity.houseId,
            name = entity.name,
            paid = entity.paid,
            town = entity.town
        )
    }

    override fun mapToEntity(domainModel: HouseDomain): HouseDB {
        return HouseDB(
            characterLocalId = null,
            houseId = domainModel.houseId,
            name = domainModel.name,
            paid = domainModel.paid,
            town = domainModel.town
        )
    }
}