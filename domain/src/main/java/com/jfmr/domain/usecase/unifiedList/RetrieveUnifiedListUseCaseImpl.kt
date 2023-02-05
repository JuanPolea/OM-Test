package com.jfmr.domain.usecase.unifiedList

import com.jfmr.domain.repository.unifiedList.UnifiedListRepository
import javax.inject.Inject

class RetrieveUnifiedListUseCaseImpl @Inject constructor(
    private val repository: UnifiedListRepository
) : RetrieveUnifiedListUseCase {

    override suspend fun invoke() =
        repository.retrieveUnifiedList()

}
