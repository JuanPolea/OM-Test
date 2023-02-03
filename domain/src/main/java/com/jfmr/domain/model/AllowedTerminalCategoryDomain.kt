package com.jfmr.domain.model

data class AllowedTerminalCategoryDomain(
    val externalId: String,
    val maxTerminals: Int,
    val maxTerminalsOfNonOperator: Int,
    val name: String,
    val responseElementType: String
)
