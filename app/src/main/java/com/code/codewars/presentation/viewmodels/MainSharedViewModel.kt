package com.code.codewars.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.domain.interactors.DeleteLocalChallengesUseCase
import com.code.codewars.domain.interactors.GetLocalChallengesUseCase
import com.code.codewars.domain.interactors.GetRemoteChallengesUseCase
import com.code.codewars.domain.interactors.InsertLocalChallengesUseCase
import com.code.codewars.utils.DataState
import com.code.codewars.utils.EspressoIdlingResource
import com.code.codewars.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    private val getLocalChallengesUseCase: GetLocalChallengesUseCase,
    private val getRemoteChallengesUseCase: GetRemoteChallengesUseCase,
    private val insertLocalChallengesUseCase: InsertLocalChallengesUseCase,
    private val deleteLocalChallengesUseCase: DeleteLocalChallengesUseCase,
    @Named("io") private val ioScheduler: Scheduler,
) : ViewModel() {

    private val _challengesState = SingleLiveEvent<DataState<List<ChallengeDto>>>()
    var challengesState: LiveData<DataState<List<ChallengeDto>>> = _challengesState

    private var disposable: Disposable? = null

    fun getAuthoredChallenges(userName: String) {
        EspressoIdlingResource.increment()
        _challengesState.postValue(DataState.Loading())
        disposable = getRemoteChallengesUseCase.invoke(user = userName)
            .flatMapCompletable { list ->
                deleteLocalChallengesUseCase.invoke()
                    .andThen(insertLocalChallengesUseCase.invoke(list))
            }.andThen(getLocalChallengesUseCase.invoke())
            .subscribeOn(ioScheduler)
            .subscribeBy(
                onSuccess = {
                    _challengesState.postValue(DataState.Success(it))
                    EspressoIdlingResource.decrement()
                    Timber.d("Successfully retrieved ChallengesDto size:${it.size}")
                },
                onError = {
                    _challengesState.postValue(DataState.Error(it.message))
                    Timber.d("Failed to retrieve ChallengesDto :${it.message}")
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }
}
