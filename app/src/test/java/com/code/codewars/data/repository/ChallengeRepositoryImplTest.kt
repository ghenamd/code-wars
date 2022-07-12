package com.code.codewars.data.repository

import com.code.codewars.data.local.ChallengeDao
import com.code.codewars.data.parser.ChallengeParser
import com.code.codewars.data.remote.ApiResponse
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.utils.MockCodeWarsApi
import com.code.codewars.utils.TestUtils.generateListOfDto
import com.code.codewars.utils.TestUtils.generateListOfEntities
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import retrofit2.HttpException

internal class ChallengeRepositoryImplTest {

    private val server = MockCodeWarsApi.mockWebServer
    private val parser = mock<ChallengeParser>()
    private val api = MockCodeWarsApi.api
    private val dao = mock<ChallengeDao>()
    private val SUT by lazy { ChallengeRepositoryImpl(api, dao, parser) }

    @Test
    fun `GIVEN a valid user name THEN fetch a list of ChallengeDto from the server`() {
        val user = "user"
        val expected = MockCodeWarsApi.expectedList

        server.enqueue(
            MockResponse().setBody(
                MockCodeWarsApi.testBody
            )
        )

        api.getUserAuthoredChallenges(user)
            .test()
            .await()
            .assertValue(ApiResponse(expected))
    }

    @Test
    fun `GIVEN a valid user name WHEN a server error is hit THEN assert the error code matches`() {
        val user = "user"
        server.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
        api.getUserAuthoredChallenges(user)
            .test()
            .await()
            .assertError {
                it is HttpException &&
                        it.code() == 400
            }
    }

    @Test
    fun `GIVEN a valid user name WHEN ApiResponse has no data is hit THEN assert it returns an empty list`() {
        val user = "user"
        server.enqueue(MockResponse().setResponseCode(200).setBody("{\"data\": []}"))
        api.getUserAuthoredChallenges(user)
            .test()
            .await()
            .assertValue(ApiResponse(emptyList()))
    }

    @Test
    fun `GIVEN a list of ChallengeEntity WHEN inserted into local Db THEN assert is successful`() {
        val input = generateListOfDto()

        whenever(dao.insertChallenges(parser.toEntity(input))).thenReturn(Completable.complete())

        val test = SUT.saveChallenges(input).test()
        test.assertComplete()
    }

    @Test
    fun `get a list of ChallengeDao from local Db THEN assert is not empty`() {
        val dbList = generateListOfEntities()
        val parsed = generateListOfDto()

        whenever(dao.getChallenges()).thenReturn(Single.just(dbList))
        whenever(parser.toDto(dbList)).thenReturn(parsed)

        val test = SUT.getLocalChallenges().test()
        test.assertValue(parsed)
    }

    @Test
    fun `get a list of ChallengeDao from local Db THEN assert return correct Data`() {
        val dbList = generateListOfEntities()
        val parsed = generateListOfDto()

        whenever(dao.getChallenges()).thenReturn(Single.just(dbList))
        whenever(parser.toDto(dbList)).thenReturn(parsed)

        val test = SUT.getLocalChallenges().test()
        test.assertValueAt(0) { list ->
            list[0] == ChallengeDto(
                id = "5571d9fc11526780a000011a",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            )
        }
    }
}
