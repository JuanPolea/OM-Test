@file:Suppress("TooManyFunctions")

package com.jfmr.omtest.data.model.extensions

import com.jfmr.domain.model.rtv1.AllowedTerminalCategoryDomain
import com.jfmr.domain.model.rtv1.AttachmentDomain
import com.jfmr.domain.model.rtv1.AwardDomain
import com.jfmr.domain.model.rtv1.EncodingDomain
import com.jfmr.domain.model.rtv1.ExtrafieldDomain
import com.jfmr.domain.model.rtv1.GenreEntityDomain
import com.jfmr.domain.model.rtv1.MetadataDomain
import com.jfmr.domain.model.rtv1.MetadataXDomain
import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import com.jfmr.domain.model.rtv1.ResponseDomain
import com.jfmr.domain.model.rtv1.SecurityGroupDomain
import com.jfmr.domain.model.rtv1.TvShowReferenceDomain
import com.jfmr.domain.model.rtv1.RTV1Domain
import com.jfmr.omtest.data.model.rtv1.AllowedTerminalCategory
import com.jfmr.omtest.data.model.rtv1.Attachment
import com.jfmr.omtest.data.model.rtv1.Award
import com.jfmr.omtest.data.model.rtv1.Encoding
import com.jfmr.omtest.data.model.rtv1.Extrafield
import com.jfmr.omtest.data.model.rtv1.GenreEntity
import com.jfmr.omtest.data.model.rtv1.Metadata
import com.jfmr.omtest.data.model.rtv1.MetadataX
import com.jfmr.omtest.data.model.rtv1.RTV1DetailResponse
import com.jfmr.omtest.data.model.rtv1.Response
import com.jfmr.omtest.data.model.rtv1.SecurityGroup
import com.jfmr.omtest.data.model.rtv1.TvShowReference
import com.jfmr.omtest.data.model.rtv1.RTV1Response

private const val BASE_IMAGE_URL = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/images"

object RTV1Extensions {
    internal fun RTV1Response.toDomain(): RTV1Domain =
        RTV1Domain(
            metadataDomain = metadata.toDomain(),
            responseDomain = response.map { it.toDomain() }
        )


    internal fun RTV1DetailResponse.toDetailDomain(): RTV1DetailDomain =
        RTV1DetailDomain(
            metadata = metadata.toDomain(),
            response = response.toDomain()
        )

    private fun Metadata.toDomain(): MetadataDomain =
        MetadataDomain(
            fullLength = fullLength,
            request = request,
            timestamp = timestamp
        )

    private fun Response.toDomain(): ResponseDomain =
        ResponseDomain(
            adsInfo = adsInfo,
            advisories = advisories,
            allowedTerminalCategories = allowedTerminalCategories.map { it.toDomain() },
            assetExternalId = assetExternalId,
            assetId = assetId,
            attachments = attachments.map { it.toDomain() },
            awards = awards.map { it.toDomain() },
            broadcastTime = broadcastTime,
            categoryId = categoryId,
            chapters = chapters,
            contentProvider = contentProvider,
            contentProviderExternalId = contentProviderExternalId,
            definition = definition,
            description = description,
            discountId = discountId,
            duration = duration,
            encodings = encodings.map { it.toDomain() },
            episodeId = episodeId,
            externalChannelId = externalChannelId,
            externalId = externalId,
            extraFields = extraFields.map { it.toDomain() },
            flags = flags,
            genreEntityList = genreEntityList.map { it.toDomain() },
            genres = genres,
            id = id,
            isSecured = isSecured,
            keywords = keywords,
            metadata = metadata.map { it.toDomain() },
            name = name,
            plannedPublishDate = plannedPublishDate,
            prLevel = prLevel,
            prName = prName,
            pricingMatrixId = pricingMatrixId,
            removalDate = removalDate,
            rentalPeriod = rentalPeriod,
            rentalPeriodUnit = rentalPeriodUnit,
            responseElementType = responseElementType,
            reviewerRating = reviewerRating,
            reviews = reviews,
            securityGroups = securityGroups.map { it.toDomain() },
            seriesName = seriesName,
            seriesNumberOfEpisodes = seriesNumberOfEpisodes,
            seriesSeason = seriesSeason,
            shortName = shortName,
            simultaneousViewsLimit = simultaneousViewsLimit,
            status = status,
            template = template,
            tvShowReference = tvShowReference.toDomain(),
            type = type,
            windowEnd = windowEnd,
            windowStart = windowStart,
            year = year,
        )

    private fun AllowedTerminalCategory.toDomain() =
        AllowedTerminalCategoryDomain(
            externalId = externalId,
            maxTerminals = maxTerminals,
            maxTerminalsOfNonOperator = maxTerminalsOfNonOperator,
            name = name,
            responseElementType = responseElementType
        )

    private fun Attachment.toDomain(): AttachmentDomain =
        AttachmentDomain(
            assetId = assetId,
            name = name,
            responseElementType = responseElementType,
            value = "$BASE_IMAGE_URL${value}",
            assetName = assetName,
        )

    private fun Award.toDomain(): AwardDomain =
        AwardDomain(
            responseElementType = responseElementType,
            title = title,
            year = year
        )

    private fun Encoding.toDomain(): EncodingDomain =
        EncodingDomain(
            name = name,
            responseElementType = responseElementType
        )

    private fun Extrafield.toDomain(): ExtrafieldDomain =
        ExtrafieldDomain(
            name = name,
            responseElementType = responseElementType,
            value = value
        )

    private fun GenreEntity.toDomain(): GenreEntityDomain =
        GenreEntityDomain(
            externalId = externalId,
            name = name,
            responseElementType = responseElementType,
            parentName = parentName,
            id = id,
        )

    private fun MetadataX.toDomain(): MetadataXDomain =
        MetadataXDomain(
            responseElementType = responseElementType,
            value = value,
            name = name,
        )

    private fun SecurityGroup.toDomain(): SecurityGroupDomain =
        SecurityGroupDomain(
            externalId = externalId,
            responseElementType = responseElementType,
            type = type,
        )

    private fun TvShowReference.toDomain(): TvShowReferenceDomain =
        TvShowReferenceDomain()
}
