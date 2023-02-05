package com.jfmr.omtest.data.model.stv

data class Availability(
    val availabilityProperties: List<Any>?,
    val categories: List<Category>?,
    val endTime: Long?,
    val images: List<ImageX>?,
    val serviceId: String?,
    val startTime: Long?,
    val videoId: String?
)
