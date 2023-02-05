package com.jfmr.domain.model.rtv1

data class AllowedTerminalCategoryDomain(
    val externalId: String,
    val maxTerminals: Int,
    val maxTerminalsOfNonOperator: Int,
    val name: String,
    val responseElementType: String
)
