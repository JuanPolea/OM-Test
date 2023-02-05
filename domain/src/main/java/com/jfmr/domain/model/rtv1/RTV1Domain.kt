package com.jfmr.domain.model.rtv1

data class RTV1Domain(
    val metadataDomain: MetadataDomain = MetadataDomain(),
    val responseDomain: List<ResponseDomain> = emptyList(),
)
