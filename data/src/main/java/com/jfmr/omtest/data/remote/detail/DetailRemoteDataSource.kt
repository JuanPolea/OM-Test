package com.jfmr.omtest.data.remote.detail

import com.jfmr.omtest.data.model.rt.v1.RTV1DetailResponse
import retrofit2.Response

interface DetailRemoteDataSource {

    suspend fun retrieveDetail(externalId: String): Response<RTV1DetailResponse>
}
