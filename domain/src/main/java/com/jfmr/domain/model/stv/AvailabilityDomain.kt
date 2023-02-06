package com.jfmr.domain.model.stv

data class AvailabilityDomain(
    val availabilityProperties: List<Any> = emptyList(),
    val categories: List<CategoryDomain> = emptyList(),
    val endTime: Long = 0L,
    val images: List<ImageXDomain> = emptyList(),
    val serviceId: String = "",
    val startTime: Long = 0L,
    val videoId: String = "",
)
