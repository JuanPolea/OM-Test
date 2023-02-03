package com.jfmr.domain.model

data class GenreEntityDomain(
    val externalId: String,
    val id: Long,
    val name: String,
    val parentName: String,
    val responseElementType: String
)
