package com.example.tibia.model.remote

import com.google.gson.annotations.SerializedName

data class CharacterRequest(
    @SerializedName("account_badges")
    val accountBadges: List<AccountBadge>? = null,

    @SerializedName("account_information")
    val accountInformation: AccountInformation? = null,

    @SerializedName("achievements")
    val achievements: List<Achievement>? = null,

    @SerializedName("character")
    val character: CharacterDetails? = null,

    @SerializedName("deaths")
    val deaths: List<Death>? = null,

    @SerializedName("deaths_truncated")
    val deathsTruncated: Boolean? = null,

    @SerializedName("other_characters")
    val otherCharacters: List<OtherCharacter>? = null
)

// Subclasses para o request
data class AccountBadge(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class AccountInformation(
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("loyalty_title")
    val loyaltyTitle: String? = null,
    @SerializedName("position")
    val position: String? = null
)

data class Achievement(
    @SerializedName("grade")
    val grade: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("secret")
    val secret: Boolean? = null
)

data class CharacterDetails(
    @SerializedName("account_status")
    val accountStatus: String? = null,
    @SerializedName("achievement_points")
    val achievementPoints: Int? = null,
    @SerializedName("comment")
    val comment: String? = null,
    @SerializedName("deletion_date")
    val deletionDate: String? = null,
    @SerializedName("former_names")
    val formerNames: List<String>? = null,
    @SerializedName("former_worlds")
    val formerWorlds: List<String>? = null,
    @SerializedName("guild")
    val guild: Guild? = null,
    @SerializedName("houses")
    val houses: List<House>? = null,
    @SerializedName("last_login")
    val lastLogin: String? = null,
    @SerializedName("level")
    val level: Int? = null,
    @SerializedName("married_to")
    val marriedTo: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("position")
    val position: String? = null,
    @SerializedName("residence")
    val residence: String? = null,
    @SerializedName("sex")
    val sex: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("traded")
    val traded: Boolean? = null,
    @SerializedName("unlocked_titles")
    val unlockedTitles: Int? = null,
    @SerializedName("vocation")
    val vocation: String? = null,
    @SerializedName("world")
    val world: String? = null
)

data class Guild(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rank")
    val rank: String? = null
)

data class House(
    @SerializedName("houseid")
    val houseId: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("paid")
    val paid: String? = null,
    @SerializedName("town")
    val town: String? = null
)

data class Death(
    @SerializedName("assists")
    val assists: List<KillerOrAssist>? = null,
    @SerializedName("killers")
    val killers: List<KillerOrAssist>? = null,
    @SerializedName("level")
    val level: Int? = null,
    @SerializedName("reason")
    val reason: String? = null,
    @SerializedName("time")
    val time: String? = null
)

data class KillerOrAssist(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("player")
    val player: Boolean? = null,
    @SerializedName("summon")
    val summon: String? = null,
    @SerializedName("traded")
    val traded: Boolean? = null
)

data class OtherCharacter(
    @SerializedName("deleted")
    val deleted: Boolean? = null,
    @SerializedName("main")
    val main: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("position")
    val position: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("traded")
    val traded: Boolean? = null,
    @SerializedName("world")
    val world: String? = null
)
