package com.jfmr.domain.repository.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain

interface DetailRepository {

    suspend fun retrieveDetail(externalId: String): RTV1DetailDomain?
}
