package com.code.codewars.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChallengeDto(
    val id: String,
    val name: String,
    val description: String,
    val rank: Int?,
    val rankName: String?,
    val tags: List<String>,
    val languages: List<String>
) : Parcelable
