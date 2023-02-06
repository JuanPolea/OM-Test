package com.jfmr.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfmr.domain.di.QDetailUseCase
import com.jfmr.domain.usecase.detail.DetailUseCase
import com.jfmr.presentation.detail.model.DetailEvent
import com.jfmr.presentation.detail.model.DetailState
import com.jfmr.presentation.detail.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @QDetailUseCase private val detailUseCase: DetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Loading)
    internal val detailState: MutableStateFlow<DetailState> = _detailState


    fun getDetail(externalId: String) {
        Timber.wtf(" Stae ${savedStateHandle.get<String>("id")}")
        _detailState.update {
            DetailState.Loading
        }
        viewModelScope.launch {
            detailUseCase
                .invoke(externalId)
                .collectLatest {
                    it?.let { rtDomain ->
                        Timber.wtf(" rtDomain ${rtDomain.toUI()}")
                        _detailState.update {
                            DetailState.Success(rtDomain.toUI())
                        }
                    }
                }
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetDetail -> {
                getDetail(event.id)
            }

            DetailEvent.NavigateBack -> _detailState.update {
                DetailState.NavigateBack
            }

            is DetailEvent.OnRecommendedCliked -> {
            }
        }
    }
}
