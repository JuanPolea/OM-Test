package com.jfmr.domain.usecase.detail

import com.jfmr.domain.repository.detail.DetailRepository
import com.jfmr.domain.repository.recommendations.RecommendationsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class DetailUseCaseImpl @Inject constructor(
    private val detailRepository: DetailRepository,
    private val recommendationsRepository: RecommendationsRepository,
) : DetailUseCase {

    override operator fun invoke(externalId: String) = flow {
        detailRepository.retrieveDetail(externalId)
            .zip(recommendationsRepository.retrieveRecommendations(externalId)) { basicInformation, recommendations ->
                basicInformation?.copy(recommendations = recommendations)
            }.first()
            .also {
                emit(it)
            }
    }
}
