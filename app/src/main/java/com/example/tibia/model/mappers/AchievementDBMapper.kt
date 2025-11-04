package com.example.tibia.model.mappers

import com.example.tibia.model.domain.AchievementDomain
import com.example.tibia.model.entity.AchievementDB
import com.example.tibia.model.remote.Achievement

class AchievementDBMapper : EntityMapper<AchievementDB, AchievementDomain>() {
    override fun mapFromEntity(entity: AchievementDB): AchievementDomain {
        return AchievementDomain(
            name = entity.name,
            grade = entity.grade,
            secret = entity.secret
        )
    }

    override fun mapToEntity(domainModel: AchievementDomain): AchievementDB {
        return AchievementDB(
            characterLocalId = null,
            name = domainModel.name,
            grade = domainModel.grade,
            secret = domainModel.secret
        )
    }
}