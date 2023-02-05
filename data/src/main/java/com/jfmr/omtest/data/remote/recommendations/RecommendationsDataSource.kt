package com.jfmr.omtest.data.remote.recommendations

import com.jfmr.omtest.data.model.stv.STVResponse
import retrofit2.Response

interface RecommendationsDataSource {

    suspend fun retrieveRecommendations(externalId: String): Response<STVResponse>
}
