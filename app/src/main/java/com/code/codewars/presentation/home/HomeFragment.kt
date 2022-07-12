package com.code.codewars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieDrawable
import com.code.codewars.R
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.databinding.FragmentHomeBinding
import com.code.codewars.domain.model.ChallengeCallback
import com.code.codewars.presentation.viewmodels.MainSharedViewModel
import com.code.codewars.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MainSharedViewModel by activityViewModels()
    private val callback get() = activity as? ChallengeCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false)
            .also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeChallengesState()
    }

    override fun onResume() {
        super.onResume()
        binding.homeEdt.show()
        binding.homeFetchBtn.show()
    }

    private fun observeChallengesState() {
        viewModel.challengesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DataState.Error -> showError()
                is DataState.Loading -> showLoading()
                is DataState.Success -> showSuccess(state.invoke())
            }
        }
    }

    private fun showSuccess(list: List<ChallengeDto>) {
        callback?.onShowChallengeList(list)
        binding.homeLottieView.cancelAnimation()
        binding.homeLottieView.remove()
    }

    private fun showLoading() {
        binding.homeEdt.hide()
        binding.homeFetchBtn.hide()

        binding.homeLottieView.show()
        binding.homeLottieView.playAnimation()
        binding.homeLottieView.repeatCount = LottieDrawable.INFINITE
    }

    private fun showError() {
        requireActivity().showToast(R.string.challenge_list_error_toast)
        binding.homeLottieView.remove()
        binding.homeEdt.show()
        binding.homeFetchBtn.show()
    }

    private fun initViews() {
        binding.homeFetchBtn.setOnClickListener(fetchChallengesListener())
    }

    private fun fetchChallengesListener(): View.OnClickListener {
        return View.OnClickListener {
            requireActivity().hideKeyboard()
            val userName = binding.homeEdt.text?.trim().toString()
            when (userName.isEmpty()) {
                true -> requireActivity().showToast(R.string.challenge_list_null_user_toast)
                else -> viewModel.getAuthoredChallenges(userName)
            }
        }
    }
}
