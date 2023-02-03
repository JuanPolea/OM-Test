package com.jfmr.omtest.data.api

import com.jfmr.omtest.data.api.EndPoints.UNIFIED_URL
import com.jfmr.omtest.data.model.UnifiedListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OMService {

    @GET(UNIFIED_URL)
    suspend fun retrieveUnifiedList(
        @Query("statuses") statuses: String? = "published",
        @Query("definitions") definitions: String? = "SD;HD;4K",
        @Query("external_category_id") externalCategoryId: String? = "U7D_14028_ROW",
        @Query("filter_empty_categories") filterEmptyCategories: Boolean? = true,
        @Query("client") client: String? = "json",
    ): Response<UnifiedListResponse>

}
