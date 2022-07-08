package com.code.codewars.domain.interactors

import com.code.codewars.domain.repository.ChallengeRepository
import com.code.codewars.utils.TestUtils
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class GetLocalChallengesUseCaseTest{

    private val repository = mock<ChallengeRepository>()
    private val SUT by lazy { GetLocalChallengesUseCase(repository) }

    @Test
    fun `GIVEN ChallengeRepository WHEN a list of ChallengeDto is required THEN return the correct list`() {
        val expected = TestUtils.generateListOfDto()

        whenever(repository.getLocalChallenges()).thenReturn(Single.just(expected))
        val result = SUT.invoke().test()
        result.assertValue(expected)
    }

    @Test
    fun `GIVEN ChallengeRepository WHEN a list of ChallengeDto is required and an Error is hit  THEN return the error`() {
        val throwable = Throwable("Db error")
        whenever(repository.getLocalChallenges()).thenReturn(Single.error(throwable))
        val result = SUT.invoke().test()
        result.assertError(throwable)
    }
}