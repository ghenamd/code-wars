package com.code.codewars.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.codewars.R
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.databinding.FragmentChallengeDetailsBinding
import com.code.codewars.utils.Common
import com.code.codewars.utils.Common.CHALLENGE_DTO
import com.code.codewars.utils.setSpannable

class ChallengeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentChallengeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentChallengeDetailsBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val challenge = requireArguments().getParcelable<ChallengeDto>(CHALLENGE_DTO)
        initViews(challenge)
    }

    private fun initViews(challenge: ChallengeDto?) {
        binding.challengeDetailsDescription.text = challenge?.description
        binding.challengeDetailsName.text = challenge?.name

        val text = String.format(
            binding.root.context.getString(R.string.challenge_item_rank_name),
            challenge?.rankName ?: Common.UNKNOWN
        )
        binding.challengeDetailsRank.setSpannable(text, requireActivity())
    }
}
