package com.code.codewars.domain.interactors

import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.domain.repository.ChallengeRepository
import io.reactivex.Single
import javax.inject.Inject

class GetLocalChallengesUseCase @Inject constructor(
    private val repository: ChallengeRepository
) {
    fun invoke(): Single<List<ChallengeDto>> {
        return repository.getLocalChallenges()
    }
}
