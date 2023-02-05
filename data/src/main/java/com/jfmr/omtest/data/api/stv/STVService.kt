package com.jfmr.omtest.data.api.stv

import com.jfmr.omtest.data.api.stv.STVEndPoints.GET_RECOMMENDATION_URL
import com.jfmr.omtest.data.model.stv.STVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface STVService {

    @GET(GET_RECOMMENDATION_URL)
    suspend fun retrieveContentRecommendations(
        @Query("params") params: String,
        @Query("client") client: String? = "json",
        @Query("services") services: String? = "2424VIDEO",
        @Query("type") type: String? = "all",
        @Query("subscription") subscription: Boolean? = false,
        @Query("max_pr_level") maxPrLevel: Int? = 8,
        @Query("filter_viewed_content") filterEmptyContent: Boolean? = true,
        @Query("max_results") max: Int? = 10,
        @Query("content_type") ct: String? = "movie",
        @Query("blend") blend: String? = "ar_od_blend_2424video",
        @Query("quality") quality: String? = "SD,HD",
        @Query("random") random: Boolean? = true,
    ): Response<STVResponse>

}
