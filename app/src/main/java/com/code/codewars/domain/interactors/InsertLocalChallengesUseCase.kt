package com.code.codewars.domain.interactors

import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.domain.repository.ChallengeRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertLocalChallengesUseCase @Inject constructor(
    private val repository: ChallengeRepository
) {

    fun invoke(list: List<ChallengeDto>): Completable {
        return repository.saveChallenges(list)
    }
}
