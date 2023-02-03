package com.jfmr.omtest.data.model

data class AllowedTerminalCategory(
    val externalId: String,
    val maxTerminals: Int,
    val maxTerminalsOfNonOperator: Int,
    val name: String,
    val responseElementType: String
)
