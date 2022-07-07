package com.code.codewars.domain.repository

import com.code.codewars.data.remote.ChallengeDto
import io.reactivex.Completable
import io.reactivex.Single

interface ChallengeRepository {
    fun getRemoteChallenges(user: String): Single<List<ChallengeDto>>
    fun saveChallenges(challenges: List<ChallengeDto>): Completable
    fun getLocalChallenges(): Single<List<ChallengeDto>>
    fun deleteLocalChallenges(): Completable
}