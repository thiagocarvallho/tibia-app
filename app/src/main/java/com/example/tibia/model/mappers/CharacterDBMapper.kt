package com.example.tibia.model.mappers

import com.example.tibia.model.CharacterWithRelations
import com.example.tibia.model.DeathWithRelations
import com.example.tibia.model.domain.CharacterDomain
import com.example.tibia.model.entity.CharacterDB
import android.R
import java.util.*


class CharacterDBMapper(
    private val badgeMapper: AccountBadgeDBMapper,
    private val achievementMapper: AchievementDBMapper,
    private val guildMapper: GuildDBMapper,
    private val houseMapper: HouseDBMapper,
    private val deathMapper: DeathDBMapper,
    private val assistMapper: DeathAssistDBMapper,
    private val killerMapper: DeathKillerDBMapper,
    private val otherCharMapper: OtherCharacterDBMapper
) : EntityMapper<CharacterWithRelations, CharacterDomain>() {

    override fun mapFromEntity(entity: CharacterWithRelations): CharacterDomain {
        return CharacterDomain(
            name = entity.character.name,
            vocation = entity.character.vocation,
            level = entity.character.level,
            sex = entity.character.sex,
            world = entity.character.world,
            residence = entity.character.residence,
            marriedTo = entity.character.marriedTo,
            accountStatus = entity.character.accountStatus,
            lastLogin = entity.character.lastChange,

            accountBadges = entity.badges.map { badgeMapper.mapFromEntity(it) },
            achievements = entity.achievements.map { achievementMapper.mapFromEntity(it) },
            guild = entity.guilds?.let { guildMapper.mapFromEntity(it) },
            houses = entity.houses.map { houseMapper.mapFromEntity(it) },
            deaths = entity.deaths.map { deathWithRelations ->
                val d = deathMapper.mapFromEntity(deathWithRelations.death)
                d.copy(
                    assists = deathWithRelations.assists.map { assistMapper.mapFromEntity(it) },
                    killers = deathWithRelations.killers.map { killerMapper.mapFromEntity(it) }
                )
            },
            otherCharacters = entity.otherCharacters.map { otherCharMapper.mapFromEntity(it) }
        )
    }

    override fun mapToEntity(domainModel: CharacterDomain): CharacterWithRelations {
        val characterDB = CharacterDB(
            name = domainModel.name,
            vocation = domainModel.vocation,
            level = domainModel.level,
            sex = domainModel.sex,
            world = domainModel.world,
            residence = domainModel.residence,
            marriedTo = domainModel.marriedTo,
            accountStatus = domainModel.accountStatus,
            lastChange = domainModel.lastLogin
        )

        val badges = domainModel.accountBadges.map { badgeMapper.mapToEntity(it) }
        val achievements = domainModel.achievements.map { achievementMapper.mapToEntity(it) }
        val guildDB = domainModel.guild?.let { guildMapper.mapToEntity(it) }
        val houses = domainModel.houses.map { houseMapper.mapToEntity(it) }
        val otherChars = domainModel.otherCharacters.map { otherCharMapper.mapToEntity(it) }

        val deaths = domainModel.deaths.map { death ->
            val deathDB = deathMapper.mapToEntity(death)
            val assists = death.assists.map { assistMapper.mapToEntity(it) }
            val killers = death.killers.map { killerMapper.mapToEntity(it) }
            DeathWithRelations(deathDB, assists, killers)
        }

        return CharacterWithRelations(
            character = characterDB,
            badges = badges,
            achievements = achievements,
            guilds = guildDB,
            houses = houses,
            deaths = deaths,
            otherCharacters = otherChars
        )
    }
}
