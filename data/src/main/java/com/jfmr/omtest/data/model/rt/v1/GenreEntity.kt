package com.jfmr.omtest.data.model.rt.v1

data class GenreEntity(
    val externalId: String,
    val id: Long,
    val name: String,
    val parentName: String,
    val responseElementType: String
)
