package com.example.tibia.model.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "characterDB",
    indices = [Index(
        value = [
            "cloud_id",
            "name",
            "world"
        ],
        unique = true
    )]
)
data class CharacterDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    val localId: Int? = null,

    @ColumnInfo(name = "cloud_id")
    val cloudId: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "world")
    val world: String? = null,

    @ColumnInfo(name = "level")
    val level: Int? = null,

    @ColumnInfo(name = "vocation")
    val vocation: String? = null,

    @ColumnInfo(name = "sex")
    val sex: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "position")
    val position: String? = null,

    @ColumnInfo(name = "residence")
    val residence: String? = null,

    @ColumnInfo(name = "married_to")
    val marriedTo: String? = null,

    @ColumnInfo(name = "achievement_points")
    val achievementPoints: Int? = null,

    @ColumnInfo(name = "account_status")
    val accountStatus: String? = null,

    @ColumnInfo(name = "comment")
    val comment: String? = null,

    @ColumnInfo(name = "deletion_date")
    val deletionDate: String? = null,

    @ColumnInfo(name = "account_information")
    val accountInformation: String? = null, // JSON String

    @ColumnInfo(name = "account_badges")
    val accountBadges: String? = null, // JSON String

    @ColumnInfo(name = "achievements")
    val achievements: String? = null, // JSON String

    @ColumnInfo(name = "guild")
    val guild: String? = null, // JSON String

    @ColumnInfo(name = "houses")
    val houses: String? = null, // JSON String

    @ColumnInfo(name = "deaths")
    val deaths: String? = null, // JSON String

    @ColumnInfo(name = "other_characters")
    val otherCharacters: String? = null, // JSON String

    @ColumnInfo(name = "deaths_truncated")
    val deathsTruncated: Boolean? = null,

    @ColumnInfo(name = "is_synchronized")
    var isSynchronized: Boolean = false,

    @ColumnInfo(name = "is_delete")
    var isDelete: Boolean = false,

    @ColumnInfo(name = "is_edited")
    var isEdited: Boolean = false,

    @ColumnInfo(name = "is_created")
    var isCreated: Boolean = false,

    @ColumnInfo(name = "last_change")
    var lastChange: String? = null
)
