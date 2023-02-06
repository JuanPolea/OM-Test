package com.jfmr.presentation.detail.model

import android.os.Parcelable
import com.jfmr.domain.model.rtv1.AwardDomain
import com.jfmr.domain.model.rtv1.RTV1DetailDomain
import com.jfmr.domain.model.stv.AvailabilityDomain
import com.jfmr.domain.model.stv.CategoryDomain
import com.jfmr.domain.model.stv.GenreDomain
import com.jfmr.domain.model.stv.ImageXDomain
import com.jfmr.domain.model.stv.MetadataDomain
import com.jfmr.domain.model.stv.ResponseDomain
import com.jfmr.domain.model.stv.STVResponseDomain
import com.jfmr.presentation.extensions.TypeExtensions.orDefault
import com.jfmr.presentation.unifiedList.model.AttachmentUI
import com.jfmr.presentation.unifiedList.model.toUI
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class DetailState {
    data class Success(val detail: RTV1DetailUI) : DetailState()
    data class Error(val error: String) : DetailState()
    object Loading : DetailState()
    object NavigateBack : DetailState()
}

sealed class DetailEvent {
    data class GetDetail(val id: String) : DetailEvent()
    object NavigateBack : DetailEvent()
    data class OnRecommendedCliked(val recommendedItemList: RecommendedItemList) : DetailEvent()

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
    val year: Int,
    val recommendations: STVResponseUI = STVResponseUI()
)

data class STVResponseUI(
    val metadata: MetadataUI = MetadataUI(),
    val response: List<ResponseUI> = emptyList(),
)

data class MetadataUI(
    val fullLength: Int = 0,
    val request: String = "",
    val timestamp: Long = 0L,
)

data class ResponseUI(
    val id: Int,
    val name: String,
    val genres: List<GenreUI> = emptyList(),
    val images: List<ImageXUI> = emptyList(),
    val contentProperties: List<String?> = emptyList(),
    val availabilities: List<AvailabilityUI> = emptyList(),
    val contentType: String = "",
    val externalContentId: String = "",
    val prLevel: Int,
    val prName: String = "",
    val ratersCount: Int,
    val rating: Float,
    val recommendationReasons: List<String?> = emptyList(),
    val type: String = "",
)

data class GenreUI(
    val externalId: String,
    val id: String,
    val name: String
)

@Parcelize
@Serializable
data class ImageXUI(
    val name: String,
    val value: String
) : Parcelable

data class AvailabilityUI(
    val availabilityProperties: List<Any> = emptyList(),
    val categories: List<CategoryDomain> = emptyList(),
    val endTime: Long = 0L,
    val images: List<ImageXDomain> = emptyList(),
    val serviceId: String = "",
    val startTime: Long = 0L,
    val videoId: String = "",
)

fun RTV1DetailDomain.toUI(): RTV1DetailUI =
    with(response)
    {
        RTV1DetailUI(
            id = id,
            name = name.orDefault(),
            genre = genreEntityList.joinToString(", ") { it.name },
            externalId = externalId,
            description = description.orDefault(),
            definition = definition,
            attachments = attachments.map { it.toUI() },
            awards = awards,
            duration = duration,
            reviewerRating = reviewerRating.orDefault(),
            type = type.orDefault(),
            year = year,
            recommendations = recommendations.toUI(),
        )
    }

@Parcelize
@Serializable
data class RecommendedItemList(
    val id: Int,
    val name: String,
    val imageXUI: ImageXUI,
    val externalContendId: String = "",
) : Parcelable

fun STVResponseDomain.toUI(): STVResponseUI =
    STVResponseUI(
        metadata = metadata.toUI(),
        response = response.map { it.toUI() },
    )

fun MetadataDomain.toUI(): MetadataUI =
    MetadataUI(
        fullLength = fullLength,
        request = request,
        timestamp = timestamp,
    )

fun ResponseDomain.toUI(): ResponseUI =
    ResponseUI(
        id = id,
        name = name,
        genres = genres.map { it.toUI() },
        images = images.map { it.toUI() },
        contentProperties = contentProperties,
        availabilities = availabilities.map { it.toUI() },
        contentType = contentType,
        externalContentId = externalContentId,
        prLevel = prLevel,
        prName = prName,
        ratersCount = ratersCount,
        rating = rating,
        recommendationReasons = recommendationReasons,
        type = type,
    )

fun GenreDomain.toUI(): GenreUI =
    GenreUI(
        externalId = externalId,
        id = id,
        name = name,
    )

fun ImageXDomain.toUI(): ImageXUI =
    ImageXUI(
        name = name,
        value = value,
    )

fun AvailabilityDomain.toUI(): AvailabilityUI =
    AvailabilityUI(
        availabilityProperties = availabilityProperties,
        categories = categories,
        endTime = endTime,
        images = images,
        serviceId = serviceId,
        startTime = startTime,
        videoId = videoId,
    )
