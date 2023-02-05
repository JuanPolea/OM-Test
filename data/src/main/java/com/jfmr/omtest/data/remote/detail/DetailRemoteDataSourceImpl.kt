package com.jfmr.omtest.data.remote.detail

import com.jfmr.omtest.data.api.OMService
import com.jfmr.omtest.data.model.rtv1.RTV1DetailResponse
import retrofit2.Response
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(
    private val service: OMService
) : DetailRemoteDataSource {

    override suspend fun retrieveDetail(externalId: String): Response<RTV1DetailResponse> {
        return service.retrieveDetail(id = externalId)
    }
}
