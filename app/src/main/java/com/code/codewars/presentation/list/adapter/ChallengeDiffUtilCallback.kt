package com.code.codewars.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.code.codewars.data.remote.ChallengeDto

class ChallengeDiffUtilCallback : DiffUtil.ItemCallback<ChallengeDto>() {

    override fun areItemsTheSame(oldItem: ChallengeDto, newItem: ChallengeDto): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: ChallengeDto, newItem: ChallengeDto): Boolean {
        return newItem == oldItem
    }
}
