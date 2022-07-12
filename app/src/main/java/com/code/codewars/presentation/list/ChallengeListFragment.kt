package com.code.codewars.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.databinding.FragmentChallengeListBinding
import com.code.codewars.domain.model.ChallengeCallback
import com.code.codewars.presentation.list.adapter.ChallengeAdapter
import com.code.codewars.utils.Common.CHALLENGE_DTO_LIST
import com.code.codewars.utils.remove
import com.code.codewars.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeListFragment : Fragment() {

    private lateinit var binding: FragmentChallengeListBinding

    private var mAdapter: ChallengeAdapter? = null
    private val callback get() = activity as? ChallengeCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentChallengeListBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val challenges = requireArguments().getParcelableArrayList<ChallengeDto>(CHALLENGE_DTO_LIST)
        initAdapter()
        setUiData(challenges)
    }

    private fun initAdapter() {
        mAdapter = ChallengeAdapter { item -> callback?.onShowChallengeDetails(item) }
        binding.challengeListRv.adapter = mAdapter
    }

    private fun setUiData(list: ArrayList<ChallengeDto>?) {
        when (list.isNullOrEmpty()) {
            true -> {
                binding.challengeListLottieView.show()
                binding.challengeListLottieView.playAnimation()
                binding.challengeListLottieView.repeatCount = LottieDrawable.INFINITE
                binding.challengeListInfoTv.show()
                binding.challengeListRv.remove()
            }
            else -> {
                binding.challengeListLottieView.remove()
                binding.challengeListInfoTv.remove()
                binding.challengeListRv.show()
                mAdapter?.submitList(list)
            }
        }
    }
}
