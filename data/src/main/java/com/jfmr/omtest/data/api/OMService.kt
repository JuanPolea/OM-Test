package com.jfmr.omtest.data.api

import com.jfmr.omtest.data.api.EndPoints.GET_VIDEO
import com.jfmr.omtest.data.api.EndPoints.UNIFIED_URL
import com.jfmr.omtest.data.model.rtv1.RTV1DetailResponse
import com.jfmr.omtest.data.model.rtv1.RTV1Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMService {

    @GET(UNIFIED_URL)
    suspend fun retrieveUnifiedList(
        @Query("client") client: String? = "json",
        @Query("statuses") statuses: String? = "published",
        @Query("definitions") definitions: String? = "SD;HD;4K",
        @Query("external_category_id") externalCategoryId: String? = "U7D_14028_ROW",
        @Query("filter_empty_categories") filterEmptyCategories: Boolean? = true,
    ): Response<RTV1Response>

    @GET(GET_VIDEO)
    suspend fun retrieveDetail(
        @Query("external_id") id: String,
        @Query("client") client: String? = "json",
    ): Response<RTV1DetailResponse>

}
