package com.code.codewars.utils

import com.code.codewars.data.local.ChallengeEntity
import com.code.codewars.data.remote.ChallengeDto

object TestUtils {

    fun generateListOfDto(): List<ChallengeDto> {
        return listOf(
            ChallengeDto(
                id = "5571d9fc11526780a000011a",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            ),
            ChallengeDto(
                id = "51ba717bb08c1cd60f00002f",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            )
        )
    }

    fun generateListOfEntities(): List<ChallengeEntity> {
        return listOf(
            ChallengeEntity(
                id = "5571d9fc11526780a000011a",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            ),
            ChallengeEntity(
                id = "51ba717bb08c1cd60f00002f",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            )
        )
    }
}
