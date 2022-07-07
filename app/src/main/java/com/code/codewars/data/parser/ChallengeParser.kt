package com.code.codewars.data.parser

import com.code.codewars.data.local.ChallengeEntity
import com.code.codewars.data.remote.ChallengeDto
import javax.inject.Inject

class ChallengeParser @Inject constructor() {

    fun toDto(list: List<ChallengeEntity>): List<ChallengeDto> {
        val dtoList = mutableListOf<ChallengeDto>()
        list.map { item ->
            val dto = ChallengeDto(
                id = item.id,
                name = item.name,
                description = item.description,
                rank = item.rank,
                rankName = item.rankName,
                tags = item.tags,
                languages = item.languages
            )
            dtoList.add(dto)
        }
        return dtoList
    }

    fun toEntity(list: List<ChallengeDto>): List<ChallengeEntity> {
        val entityList = mutableListOf<ChallengeEntity>()
        list.map { item ->
            val dto = ChallengeEntity(
                id = item.id,
                name = item.name,
                description = item.description,
                rank = item.rank,
                rankName = item.rankName,
                tags = item.tags,
                languages = item.languages
            )
            entityList.add(dto)
        }
        return entityList
    }
}