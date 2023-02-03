package com.jfmr.domain.repository

import com.jfmr.domain.model.UnifiedListDomain
import kotlinx.coroutines.flow.Flow

interface UnifiedListRepository {

    suspend fun retrieveUnifiedList(): Flow<UnifiedListDomain>
}
