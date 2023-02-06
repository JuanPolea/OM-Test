package com.jfmr.omtest.data.model.rt.v1

data class AllowedTerminalCategory(
    val externalId: String,
    val maxTerminals: Int,
    val maxTerminalsOfNonOperator: Int,
    val name: String,
    val responseElementType: String
)
