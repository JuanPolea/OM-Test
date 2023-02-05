package com.jfmr.domain.repository.unifiedList

import com.jfmr.domain.model.rtv1.RTV1Domain
import kotlinx.coroutines.flow.Flow

interface UnifiedListRepository {

    suspend fun retrieveUnifiedList(): Flow<RTV1Domain>
}
