package com.jfmr.omtest.data.model.stv

data class Response(
    val id: Int,
    val name: String,
    val genres: List<Genre>,
    val images: List<ImageX>,
    val contentProperties: List<String?>? = emptyList(),
    val availabilities: List<Availability?>?= emptyList(),
    val contentType: String,
    val externalContentId: String,
    val prLevel: Int,
    val prName: String,
    val ratersCount: Int,
    val rating: Float,
    val recommendationReasons: List<String?> = emptyList(),
    val type: String
)
