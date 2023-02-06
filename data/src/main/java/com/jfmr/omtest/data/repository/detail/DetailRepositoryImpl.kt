package com.jfmr.omtest.data.repository.detail

import com.jfmr.domain.repository.detail.DetailRepository
import com.jfmr.omtest.data.model.rt.v1.extensions.RTV1Extensions.toDetailDomain
import com.jfmr.omtest.data.remote.detail.DetailRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailRemoteDataSource: DetailRemoteDataSource,
) : DetailRepository {

    override  fun retrieveDetail(externalId: String) = flow {
        val response =
            detailRemoteDataSource.retrieveDetail(externalId).body()?.toDetailDomain()
        emit( response)
    }
}
