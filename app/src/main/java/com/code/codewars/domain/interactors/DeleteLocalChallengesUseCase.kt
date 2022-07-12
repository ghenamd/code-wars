package com.code.codewars.domain.interactors

import com.code.codewars.domain.repository.ChallengeRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteLocalChallengesUseCase @Inject constructor(
    private val repository: ChallengeRepository
) {
    fun invoke(): Completable {
        return repository.deleteLocalChallenges()
    }
}
