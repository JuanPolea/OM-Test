package com.jfmr.omtest.data.remote

import com.jfmr.omtest.data.model.UnifiedListResponse
import retrofit2.Response

interface UnifiedListRemoteDataSource {

    suspend fun retrieveList(): Response<UnifiedListResponse>
}
