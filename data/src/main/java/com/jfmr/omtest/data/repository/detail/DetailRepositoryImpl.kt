package com.jfmr.omtest.data.repository.detail

import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import com.jfmr.domain.repository.detail.DetailRepository
import com.jfmr.omtest.data.model.extensions.RTV1Extensions.toDetailDomain
import com.jfmr.omtest.data.remote.detail.DetailRemoteDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailRemoteDataSource: DetailRemoteDataSource,
) : DetailRepository {

    override suspend fun retrieveDetail(externalId: String): RTV1DetailDomain? {
        detailRemoteDataSource.retrieveDetail(externalId)
        val response =
            detailRemoteDataSource.retrieveDetail(externalId).body()?.toDetailDomain()
        return response
    }
}
