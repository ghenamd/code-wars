package com.code.codewars.domain.model

import com.code.codewars.data.remote.ChallengeDto

interface ChallengeCallback {
    fun onShowChallengeDetails(item: ChallengeDto)
    fun onShowChallengeList(list: List<ChallengeDto>)
}
