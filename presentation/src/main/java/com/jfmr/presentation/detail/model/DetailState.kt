package com.jfmr.presentation.detail.model

import com.jfmr.domain.model.rtv1.AwardDomain
import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import com.jfmr.presentation.extensions.TypeExtensions.orDefault
import com.jfmr.presentation.unifiedList.model.AttachmentUI
import com.jfmr.presentation.unifiedList.model.toUI

sealed class DetailState {
    data class Success(val detail: RTV1DetailUI) : DetailState()
    data class Error(val error: String) : DetailState()
    object Loading : DetailState()
    object NavigateBack : DetailState()
}

sealed class DetailEvent {
    data class GetDetail(val id: String) : DetailEvent()
    object NavigateBack : DetailEvent()
}

data class RTV1DetailUI(
    val id: Int,
    val externalId: String,
    val name: String,
    val description: String,
    val definition: String,
    val attachments: List<AttachmentUI>,
    val awards: List<AwardDomain>,
    val duration: Int,
    val genre: String,
    val reviewerRating: String,
    val type: String,
    val year: Int
)

fun RTV1DetailDomain.toUI(): RTV1DetailUI =
    with(response)
    {
        RTV1DetailUI(
            id = id,
            externalId = externalId,
            name = name.orDefault(),
            description = description.orDefault(),
            definition = definition,
            attachments = attachments.map { it.toUI() },
            awards = awards,
            duration = duration,
            genre = genreEntityList.joinToString(", ") { it.name },
            reviewerRating = reviewerRating.orDefault(),
            type = type.orDefault(),
            year = year,
        )
    }
