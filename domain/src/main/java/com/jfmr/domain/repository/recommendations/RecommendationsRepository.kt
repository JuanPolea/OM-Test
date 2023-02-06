package com.jfmr.domain.repository.recommendations

import com.jfmr.domain.model.stv.STVResponseDomain
import kotlinx.coroutines.flow.Flow

interface RecommendationsRepository {

    fun retrieveRecommendations(externalId: String): Flow<STVResponseDomain>
}
