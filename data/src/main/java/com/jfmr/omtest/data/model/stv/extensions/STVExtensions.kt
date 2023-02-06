package com.jfmr.omtest.data.model.stv.extensions

import com.jfmr.domain.model.stv.AvailabilityDomain
import com.jfmr.domain.model.stv.CategoryDomain
import com.jfmr.domain.model.stv.GenreDomain
import com.jfmr.domain.model.stv.ImageXDomain
import com.jfmr.domain.model.stv.MetadataDomain
import com.jfmr.domain.model.stv.ResponseDomain
import com.jfmr.domain.model.stv.STVResponseDomain
import com.jfmr.omtest.data.model.stv.Availability
import com.jfmr.omtest.data.model.stv.Category
import com.jfmr.omtest.data.model.stv.Genre
import com.jfmr.omtest.data.model.stv.ImageX
import com.jfmr.omtest.data.model.stv.Metadata
import com.jfmr.omtest.data.model.stv.Response
import com.jfmr.omtest.data.model.stv.STVResponse

private const val BASE_IMAGE_URL = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/images"

object STVExtensions {

    fun STVResponse.toDomain(): STVResponseDomain {
        return STVResponseDomain(
            metadata = metadata.toDomain(),
            response = response.map { it.toDomain() }
        )
    }

    private fun Metadata.toDomain(): MetadataDomain {
        return MetadataDomain(
            fullLength = fullLength,
            request = request,
            timestamp = timestamp
        )
    }

    private fun Response.toDomain(): ResponseDomain =
        ResponseDomain(
            id = id,
            name = name,
            genres = genres.map { it.toDomain() },
            images = images.map { it.toDomain() },
            contentProperties = contentProperties.orEmpty(),
            availabilities = availabilities?.filterNotNull()?.map { it.toDomain() }.orEmpty(),
            contentType = contentType,
            externalContentId = externalContentId,
            prLevel = prLevel,
            prName = prName,
            ratersCount = ratersCount,
            rating = rating,
            recommendationReasons = recommendationReasons,
            type = type
        )

    private fun Genre.toDomain(): GenreDomain =
        GenreDomain(
            id = id,
            name = name,
            externalId = externalId,
        )

    private fun ImageX.toDomain(): ImageXDomain =
        ImageXDomain(
            name = name,
            value = "$BASE_IMAGE_URL$value"
        )

    private fun Availability.toDomain() =
        AvailabilityDomain(
            availabilityProperties = availabilityProperties.orEmpty(),
            categories = categories?.map { it.toDomain() }.orEmpty(),
            endTime = endTime ?: 0L,
            images = images?.map { it.toDomain() }.orEmpty(),
            serviceId = serviceId.orEmpty(),
            startTime = startTime ?: 0L,
            videoId = videoId ?: ""
        )

    private fun Category.toDomain() =
        CategoryDomain(
            categoryId = categoryId,
            categoryName = categoryName
        )
}
