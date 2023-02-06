package com.jfmr.domain.model.rtv1

import com.jfmr.domain.model.stv.STVResponseDomain

data class RTV1DetailDomain(
    val metadata: MetadataDomain,
    val response: ResponseDomain,
    val recommendations: STVResponseDomain = STVResponseDomain()
)
