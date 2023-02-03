package com.jfmr.domain.usecase

import com.jfmr.domain.repository.UnifiedListRepository
import javax.inject.Inject

class RetrieveUnifiedListUseCaseImpl @Inject constructor(
    private val repository: UnifiedListRepository
) : RetrieveUnifiedListUseCase {

    override suspend fun invoke() =
        repository.retrieveUnifiedList()

}
