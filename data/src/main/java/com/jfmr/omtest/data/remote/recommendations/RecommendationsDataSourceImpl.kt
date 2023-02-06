package com.jfmr.omtest.data.remote.recommendations

import com.jfmr.omtest.data.api.stv.STVService
import com.jfmr.omtest.data.di.QSTVService
import com.jfmr.omtest.data.model.stv.STVResponse
import retrofit2.Response
import javax.inject.Inject

class RecommendationsDataSourceImpl @Inject constructor(
    @QSTVService private val recommendationsService: STVService,
) : RecommendationsDataSource {

    override suspend fun retrieveRecommendations(externalId: String): Response<STVResponse> {
        return recommendationsService
            .retrieveContentRecommendations(params = "external_content_id:la-momia-2017")
    }
}
