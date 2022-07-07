package com.code.codewars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ChallengeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChallengesDb : RoomDatabase() {
    abstract fun getChallengesDao(): ChallengeDao
}