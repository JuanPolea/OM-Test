package com.jfmr.domain.model

data class UnifiedListDomain(
    val metadataDomain: MetadataDomain = MetadataDomain(),
    val responseDomain: List<ResponseDomain> = emptyList(),
)
