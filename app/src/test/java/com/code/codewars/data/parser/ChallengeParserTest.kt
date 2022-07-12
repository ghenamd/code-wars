package com.code.codewars.data.parser

import com.code.codewars.data.local.ChallengeEntity
import com.code.codewars.data.remote.ChallengeDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ChallengeParserTest {

    private val SUT by lazy { ChallengeParser() }

    @Test
    fun `GIVEN a list of ChallengeDto THEN return a correct parsed ChallengeEntity list`() {
        val dtoList = listOf(
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

        val entityList = listOf(
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
        val result = SUT.toEntity(dtoList)
        assertEquals(result, entityList)
    }

    @Test
    fun `GIVEN a list of ChallengeDto WHEN data is not parsed correctly THEN assert that lists are not the same`() {
        val dtoList = listOf(
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

        val entityList = listOf(
            ChallengeEntity(
                id = "Some id",
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
        val result = SUT.toEntity(dtoList)
        assertNotEquals(result, entityList)
    }

    @Test
    fun `GIVEN a list of ChallengeEntity THEN return a correct parsed ChallengeDto list`() {
        val dtoList = listOf(
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

        val entityList = listOf(
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
        val result = SUT.toDto(entityList)
        assertEquals(result, dtoList)
    }

    @Test
    fun `GIVEN a list of ChallengeEntity WHEN data is not parsed correctly THEN assert that lists are not the same`() {
        val entityList = listOf(
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
        val dtoList = listOf(
            ChallengeDto(
                id = "Some DDD id",
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
        val result = SUT.toDto(entityList)
        assertNotEquals(result, dtoList)
    }
}
