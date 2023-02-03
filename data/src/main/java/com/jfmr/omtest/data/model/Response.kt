package com.jfmr.omtest.data.model

import com.google.gson.annotations.SerializedName

data class Response(
    val adsInfo: String,
    val advisories: String,
    val allowedTerminalCategories: List<AllowedTerminalCategory>,
    val assetExternalId: String,
    val assetId: Long,
    val attachments: List<Attachment>,
    val awards: List<Award>,
    val broadcastTime: Int,
    val categoryId: Int,
    val chapters: List<Any>,
    val contentProvider: String,
    val contentProviderExternalId: String,
    val definition: String,
    val description: String,
    val discountId: String,
    val duration: Int,
    val encodings: List<Encoding>,
    val episodeId: String,
    val externalChannelId: String,
    val externalId: String,
    @field:SerializedName("extrafields")
    val extraFields: List<Extrafield>,
    val flags: Int,
    val genreEntityList: List<GenreEntity>,
    val genres: List<Long>,
    val id: Int,
    val isSecured: Boolean,
    val keywords: String,
    val metadata: List<MetadataX>,
    val name: String,
    val plannedPublishDate: Long,
    val prLevel: Int,
    val prName: String,
    val pricingMatrixId: Long,
    val removalDate: Long,
    val rentalPeriod: String,
    val rentalPeriodUnit: String,
    val responseElementType: String,
    val reviewerRating: String,
    val reviews: List<Any>,
    val securityGroups: List<SecurityGroup>,
    val seriesName: String,
    val seriesNumberOfEpisodes: String,
    val seriesSeason: String,
    val shortName: String,
    val simultaneousViewsLimit: String,
    val status: Int,
    val template: String,
    val tvShowReference: TvShowReference,
    val type: String,
    val windowEnd: Long,
    val windowStart: Long,
    val year: Int
)