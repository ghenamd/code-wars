package com.code.codewars.domain.interactors

import com.code.codewars.domain.repository.ChallengeRepository
import com.code.codewars.utils.TestUtils
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class GetRemoteChallengesUseCaseTest {

    private val repository = mock<ChallengeRepository>()
    private val SUT by lazy { GetRemoteChallengesUseCase(repository) }

    @Test
    fun `GIVEN ChallengeRepository WHEN a list of ChallengeDto is provided THEN return the expected list`() {
        val user = "user"
        val expected = TestUtils.generateListOfDto()

        whenever(repository.getRemoteChallenges(user)).thenReturn(Single.just(expected))
        val result = SUT.invoke(user).test()
        result.assertValue(expected)
    }

    @Test
    fun `GIVEN ChallengeRepository WHEN an error is hit THEN return the error`() {
        val user = "user"
        val expected = Throwable("Network error")

        whenever(repository.getRemoteChallenges(user)).thenReturn(Single.error(expected))
        val result = SUT.invoke(user).test()
        result.assertError(expected)
    }
}