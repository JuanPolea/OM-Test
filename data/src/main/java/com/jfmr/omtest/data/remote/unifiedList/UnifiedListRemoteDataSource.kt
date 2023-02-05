package com.jfmr.omtest.data.remote.unifiedList

import com.jfmr.omtest.data.model.rtv1.RTV1Response
import retrofit2.Response

interface UnifiedListRemoteDataSource {

    suspend fun retrieveList(): Response<RTV1Response>
}
