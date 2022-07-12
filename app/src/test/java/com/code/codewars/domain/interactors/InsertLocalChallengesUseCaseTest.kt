package com.code.codewars.domain.interactors

import com.code.codewars.domain.repository.ChallengeRepository
import com.code.codewars.utils.TestUtils.generateListOfDto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Test

class InsertLocalChallengesUseCaseTest {

    private val repository = mock<ChallengeRepository>()
    private val SUT by lazy { InsertLocalChallengesUseCase(repository) }

    @Test
    fun `GIVEN a list of ChallengeEntity WHEN inserted into Db THEN assert its successful`() {
        val input = generateListOfDto()

        whenever(repository.saveChallenges(input)).thenReturn(Completable.complete())
        val result = SUT.invoke(input).test()
        result.assertComplete()
    }

    @Test
    fun `GIVEN a list of ChallengeEntity WHEN inserting into Db and an error is hit THEN return an error`() {
        val input = generateListOfDto()
        val throwable = Throwable("Db error while inserting")

        whenever(repository.saveChallenges(input)).thenReturn(Completable.error(throwable))
        val result = SUT.invoke(input).test()
        result.assertError(throwable)
    }
}
