package com.jfmr.omtest.data.repository.recommendations

import com.jfmr.domain.repository.recommendations.RecommendationsRepository
import com.jfmr.omtest.data.di.Dispatchers
import com.jfmr.omtest.data.model.stv.extensions.STVExtensions.toDomain
import com.jfmr.omtest.data.remote.recommendations.RecommendationsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecommendationsRepositoryImpl @Inject constructor(
    private val recommendationsDataSource: RecommendationsDataSource,
    @Dispatchers.DispatcherIO private val dispatcher: CoroutineDispatcher,
) : RecommendationsRepository {

    override fun retrieveRecommendations(externalId: String) = flow {
        val response =
            recommendationsDataSource.retrieveRecommendations(externalId).body()!!
        emit(response.toDomain())
    }.flowOn(dispatcher)
}
