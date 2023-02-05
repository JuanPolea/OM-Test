package com.jfmr.omtest.data.remote.detail

import com.jfmr.omtest.data.model.rtv1.RTV1DetailResponse
import retrofit2.Response

interface DetailRemoteDataSource {

    suspend fun retrieveDetail(externalId: String): Response<RTV1DetailResponse>
}
