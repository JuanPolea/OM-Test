package com.jfmr.domain.model.stv

data class ResponseDomain(
    val id: Int,
    val name: String,
    val genres: List<GenreDomain> = emptyList(),
    val images: List<ImageXDomain> = emptyList(),
    val contentProperties: List<String?> = emptyList(),
    val availabilities: List<AvailabilityDomain> = emptyList(),
    val contentType: String = "",
    val externalContentId: String = "",
    val prLevel: Int,
    val prName: String = "",
    val ratersCount: Int,
    val rating: Float,
    val recommendationReasons: List<String?> = emptyList(),
    val type: String = "",
)
