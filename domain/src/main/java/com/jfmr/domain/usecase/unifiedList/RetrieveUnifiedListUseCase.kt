package com.jfmr.domain.usecase.unifiedList

import com.jfmr.domain.model.rtv1.RTV1Domain
import kotlinx.coroutines.flow.Flow

interface RetrieveUnifiedListUseCase {

    suspend operator fun invoke(): Flow<RTV1Domain>
}
