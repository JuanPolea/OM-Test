package com.jfmr.omtest.data.repository.recommendations

import com.jfmr.domain.repository.recommendations.RecommendationsRepository
import com.jfmr.omtest.data.model.stv.extensions.STVExtensions.toDomain
import com.jfmr.omtest.data.remote.recommendations.RecommendationsDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecommendationsRepositoryImpl @Inject constructor(
    private val recommendationsDataSource: RecommendationsDataSource
) : RecommendationsRepository {

    override  fun retrieveRecommendations(externalId: String) = flow {
        emit(recommendationsDataSource.retrieveRecommendations(externalId).body()!!.toDomain())
    }
}
