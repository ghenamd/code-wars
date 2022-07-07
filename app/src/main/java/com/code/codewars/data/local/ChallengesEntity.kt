package com.code.codewars.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "challenge_db")
@TypeConverters(ArrayConverter::class)
data class ChallengeEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val rank: Int?,
    val rankName: String?,
    val tags: List<String>,
    val languages: List<String>
)
