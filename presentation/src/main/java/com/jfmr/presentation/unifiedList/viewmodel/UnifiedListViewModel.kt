package com.jfmr.presentation.unifiedList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jfmr.domain.di.QRetrieveUnifiedListUseCase
import com.jfmr.domain.usecase.RetrieveUnifiedListUseCase
import com.jfmr.presentation.unifiedList.model.UnifiedItemList
import com.jfmr.presentation.unifiedList.model.UnifiedListState
import com.jfmr.presentation.unifiedList.model.toItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UnifiedListViewModel @Inject constructor(
    @QRetrieveUnifiedListUseCase private val unifiedListUseCase: RetrieveUnifiedListUseCase,
) :
    ViewModel() {

    private val _unifiedListState: MutableStateFlow<UnifiedListState> =
        MutableStateFlow(UnifiedListState.Loading)
    internal val unifiedListState: MutableStateFlow<UnifiedListState> = _unifiedListState


    fun getUnifiedList() {
        _unifiedListState.update {
            UnifiedListState.Loading
        }
        viewModelScope.launch {
            unifiedListUseCase.invoke().collectLatest { unifiedListState ->
                Timber.wtf(
                    unifiedListState.responseDomain.map { it.toItemList() }.first().toString()
                )
                _unifiedListState.value =
                    UnifiedListState.Success(unifiedListState.responseDomain.map { it.toItemList() })
            }
        }
    }

    fun onItemClicked(it: UnifiedItemList) {
        Timber.wtf("Item clicked: $it")
    }
}
