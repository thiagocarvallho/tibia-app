package com.example.tibia.model.dao.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tibia.model.dao.AchievementDao
import com.example.tibia.model.dao.CharacterDao
import com.example.tibia.model.dao.DeathWithRelationsDao
import com.example.tibia.model.dao.GuildDao
import com.example.tibia.model.dao.HighscoreDao
import com.example.tibia.model.dao.HouseDao
import com.example.tibia.model.entity.*

@Database(
    entities = [
        HighscoreEntity::class,
        CharacterDB::class,
        OtherCharacterDB::class,
        AchievementDB::class,
        HouseDB::class,
        GuildDB::class,
        DeathKillerDB::class,
        AccountBadgeDB::class,
        DeathDB::class,
        DeathAssistDB::class
    ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun achievementDao(): AchievementDao
    abstract fun houseDao(): HouseDao
    abstract fun guildDao(): GuildDao
    abstract fun deathDao(): DeathWithRelationsDao

    abstract fun highscoreDao(): HighscoreDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tibia_database"
                )
                    // .allowMainThreadQueries() // Use apenas se realmente necessário
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
