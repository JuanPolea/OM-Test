package com.jfmr.omtest.data.model

data class GenreEntity(
    val externalId: String,
    val id: Long,
    val name: String,
    val parentName: String,
    val responseElementType: String
)
