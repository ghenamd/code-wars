package com.code.codewars.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.code.codewars.R
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.databinding.ChallengeItemBinding
import com.code.codewars.utils.Common.UNKNOWN
import com.code.codewars.utils.setSpannable

class ChallengeAdapter(
    private val callback: (ChallengeDto) -> Unit
) : ListAdapter<ChallengeDto, ChallengeViewHolder>(ChallengeDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ChallengeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ChallengeViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class ChallengeViewHolder(
    private val binding: ChallengeItemBinding,
    private val callback: (ChallengeDto) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChallengeDto) {
        binding.description.text = item.description.trim()
        binding.name.text = item.name.trim()
        val text = String.format(
            binding.root.context.getString(R.string.challenge_item_rank_name),
            item.rankName ?: UNKNOWN
        )
        binding.rankName.setSpannable(text, binding.root.context)

        binding.root.setOnClickListener {
            callback(item)
        }
    }
}
