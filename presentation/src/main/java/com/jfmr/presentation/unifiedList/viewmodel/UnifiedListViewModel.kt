package com.jfmr.presentation.unifiedList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfmr.domain.di.QRetrieveUnifiedListUseCase
import com.jfmr.domain.usecase.RetrieveUnifiedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UnifiedListViewModel @Inject constructor(
    @QRetrieveUnifiedListUseCase private val unifiedListUseCase: RetrieveUnifiedListUseCase,
) :
    ViewModel() {

    private val _unifiedListState = MutableStateFlow("")
    internal val unifiedListState = _unifiedListState


    fun getUnifiedList() {
        viewModelScope.launch {
            unifiedListUseCase.invoke().collectLatest {
                Timber.wtf(it.toString())
            }
        }
    }
}
