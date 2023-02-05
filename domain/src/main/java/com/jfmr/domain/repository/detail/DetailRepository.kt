package com.jfmr.domain.repository.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    fun retrieveDetail(externalId: String): Flow<RTV1DetailDomain?>
}
