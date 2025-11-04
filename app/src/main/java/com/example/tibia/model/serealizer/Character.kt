    package com.example.tibia.model.serealizer

    import com.google.gson.annotations.SerializedName

    data class CharacterResponse(
        @SerializedName("character")
        val character: CharacterData?,
        @SerializedName("information")
        val information: Information?
    )

    data class CharacterData(
        @SerializedName("account_badges")
        val accountBadges: List<AccountBadge>?,
        @SerializedName("account_information")
        val accountInformation: AccountInformation?,
        @SerializedName("achievements")
        val achievements: List<Achievement>?,
        @SerializedName("character")
        val characterDetail: CharacterDetail?,
        @SerializedName("deaths")
        val deaths: List<Death>?,
        @SerializedName("deaths_truncated")
        val deathsTruncated: Boolean?,
        @SerializedName("other_characters")
        val otherCharacters: List<OtherCharacter>?
    )

    data class AccountBadge(
        @SerializedName("description")
        val description: String?,
        @SerializedName("icon_url")
        val iconUrl: String?,
        @SerializedName("name")
        val name: String?
    )

    data class AccountInformation(
        @SerializedName("created")
        val created: String?,
        @SerializedName("loyalty_title")
        val loyaltyTitle: String?,
        @SerializedName("position")
        val position: String?
    )

    data class Achievement(
        @SerializedName("grade")
        val grade: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("secret")
        val secret: Boolean?
    )

    data class CharacterDetail(
        @SerializedName("account_status")
        val accountStatus: String?,
        @SerializedName("achievement_points")
        val achievementPoints: Int?,
        @SerializedName("comment")
        val comment: String?,
        @SerializedName("deletion_date")
        val deletionDate: String?,
        @SerializedName("former_names")
        val formerNames: List<String>?,
        @SerializedName("former_worlds")
        val formerWorlds: List<String>?,
        @SerializedName("guild")
        val guild: Guild?,
        @SerializedName("houses")
        val houses: List<House>?,
        @SerializedName("last_login")
        val lastLogin: String?,
        @SerializedName("level")
        val level: Int?,
        @SerializedName("married_to")
        val marriedTo: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("position")
        val position: String?,
        @SerializedName("residence")
        val residence: String?,
        @SerializedName("sex")
        val sex: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("traded")
        val traded: Boolean?,
        @SerializedName("unlocked_titles")
        val unlockedTitles: Int?,
        @SerializedName("vocation")
        val vocation: String?,
        @SerializedName("world")
        val world: String?
    )

    data class Guild(
        @SerializedName("name")
        val name: String?,
        @SerializedName("rank")
        val rank: String?
    )

    data class House(
        @SerializedName("houseid")
        val houseId: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("paid")
        val paid: String?,
        @SerializedName("town")
        val town: String?
    )

    data class Death(
        @SerializedName("assists")
        val assists: List<KillerOrAssist>?,
        @SerializedName("killers")
        val killers: List<KillerOrAssist>?,
        @SerializedName("level")
        val level: Int?,
        @SerializedName("reason")
        val reason: String?,
        @SerializedName("time")
        val time: String?
    )

    data class KillerOrAssist(
        @SerializedName("name")
        val name: String?,
        @SerializedName("player")
        val player: Boolean?,
        @SerializedName("summon")
        val summon: String?,
        @SerializedName("traded")
        val traded: Boolean?
    )

    data class OtherCharacter(
        @SerializedName("deleted")
        val deleted: Boolean?,
        @SerializedName("main")
        val main: Boolean?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("position")
        val position: String?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("traded")
        val traded: Boolean?,
        @SerializedName("world")
        val world: String?
    )

    data class Information(
        @SerializedName("api")
        val api: ApiInfo?,
        @SerializedName("status")
        val status: StatusInfo?,
        @SerializedName("tibia_urls")
        val tibiaUrls: List<String>?,
        @SerializedName("timestamp")
        val timestamp: String?
    )

    data class ApiInfo(
        @SerializedName("commit")
        val commit: String?,
        @SerializedName("release")
        val release: String?,
        @SerializedName("version")
        val version: Int?
    )

    data class StatusInfo(
        @SerializedName("error")
        val error: Int?,
        @SerializedName("http_code")
        val httpCode: Int?,
        @SerializedName("message")
        val message: String?
    )
