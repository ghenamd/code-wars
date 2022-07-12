package com.code.codewars.data.repository

import com.code.codewars.data.local.ChallengeDao
import com.code.codewars.data.parser.ChallengeParser
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.data.remote.CodeWarsApi
import com.code.codewars.domain.repository.ChallengeRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val api: CodeWarsApi,
    private val dao: ChallengeDao,
    private val parser: ChallengeParser
) : ChallengeRepository {

    override fun getRemoteChallenges(user: String): Single<List<ChallengeDto>> {
        return api.getUserAuthoredChallenges(user).map { it.data }
    }

    override fun saveChallenges(challenges: List<ChallengeDto>): Completable {
        return dao.insertChallenges(parser.toEntity(challenges))
    }

    override fun getLocalChallenges(): Single<List<ChallengeDto>> {
        return dao.getChallenges().map { parser.toDto(it) }
    }

    override fun deleteLocalChallenges(): Completable {
        return dao.deleteDb()
    }
}
