package com.jfmr.domain.model.stv

data class STVResponseDomain(
    val metadata: MetadataDomain=MetadataDomain(),
    val response: List<ResponseDomain> =emptyList(),
)
