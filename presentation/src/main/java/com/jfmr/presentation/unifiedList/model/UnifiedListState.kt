package com.jfmr.presentation.unifiedList.model

import android.os.Parcelable
import com.jfmr.domain.model.AttachmentDomain
import com.jfmr.domain.model.ResponseDomain
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface UnifiedListState {
    data class Success(
        val unifiedItemLists: List<UnifiedItemList> = emptyList(),
    ) : UnifiedListState

    data class Error(
        val error: String = "",
    ) : UnifiedListState

    object Loading : UnifiedListState
}

@Parcelize
@Serializable
data class UnifiedItemList(
    val id: Int,
    val name: String,
    val description: String,
    val year: Int,
    val attachmentUI: List<AttachmentUI>,
    val type: String,
    val genre: String,
) : Parcelable

@Parcelize
@Serializable
data class AttachmentUI(
    val assetId: String,
    val assetName: String,
    val name: String,
    val responseElementType: String,
    val value: String,
) : Parcelable

internal fun ResponseDomain.toItemList(): UnifiedItemList = UnifiedItemList(
    id = id,
    description = description,
    name = name,
    year = year,
    attachmentUI = attachments.map { it.toUI() },
    type = type,
    genre = genreEntityList.joinToString(", ") { it.name }
)

private fun AttachmentDomain.toUI(): AttachmentUI = AttachmentUI(
    assetId = assetId,
    assetName = assetName,
    name = name,
    responseElementType = responseElementType,
    value = value,
)
