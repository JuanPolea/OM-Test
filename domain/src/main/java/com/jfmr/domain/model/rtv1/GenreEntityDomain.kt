package com.jfmr.domain.model.rtv1

data class GenreEntityDomain(
    val externalId: String,
    val id: Long,
    val name: String,
    val parentName: String,
    val responseElementType: String
)
