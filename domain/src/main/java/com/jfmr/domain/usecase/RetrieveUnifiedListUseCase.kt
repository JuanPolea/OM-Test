package com.jfmr.domain.usecase

import com.jfmr.domain.model.UnifiedListDomain
import kotlinx.coroutines.flow.Flow

interface RetrieveUnifiedListUseCase {

    suspend operator fun invoke(): Flow<UnifiedListDomain>
}
