package com.code.codewars.domain.interactors

import com.code.codewars.domain.repository.ChallengeRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Test

class DeleteLocalChallengesUseCaseTest {

    private val repository = mock<ChallengeRepository>()
    private val SUT by lazy { DeleteLocalChallengesUseCase(repository) }

    @Test
    fun `GIVEN ChallengeRepository WHEN database is deleted THEN assert is successful`() {
        whenever(repository.deleteLocalChallenges()).thenReturn(Completable.complete())
        val result = SUT.invoke().test()
        result.assertComplete()
    }

    @Test
    fun `GIVEN ChallengeRepository WHEN database is deleted and an error is hit THEN assert it returns an error`() {
        val throwable = Throwable("Db delete error")
        whenever(repository.deleteLocalChallenges()).thenReturn(Completable.error(throwable))
        val result = SUT.invoke().test()
        result.assertError(throwable)
    }
}
