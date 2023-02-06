package com.jfmr.omtest.data.remote.detail

import com.jfmr.omtest.data.api.rt.v1.RTV1Service
import com.jfmr.omtest.data.di.QRTV1Service
import com.jfmr.omtest.data.model.rt.v1.RTV1DetailResponse
import retrofit2.Response
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(
    @QRTV1Service private val service: RTV1Service
) : DetailRemoteDataSource {

    override suspend fun retrieveDetail(externalId: String): Response<RTV1DetailResponse> {
        return service.retrieveDetail(id = externalId)
    }
}
