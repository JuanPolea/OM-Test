package com.jfmr.omtest.data.repository

import com.jfmr.domain.model.UnifiedListDomain
import com.jfmr.domain.repository.UnifiedListRepository
import com.jfmr.omtest.data.model.UnifiedListResponse
import com.jfmr.omtest.data.model.extensions.UnifiedListExtensions.toDomain
import com.jfmr.omtest.data.remote.UnifiedListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnifiedListRepositoryImpl @Inject constructor(
    private val unifiedListRemoteDataSource: UnifiedListRemoteDataSource,
) : UnifiedListRepository {

    override suspend fun retrieveUnifiedList(): Flow<UnifiedListDomain> = flow {
        val response = unifiedListRemoteDataSource.retrieveList()
        if (response.isSuccessful) {
            val unifiedListResponse = response.body() as UnifiedListResponse
            emit(unifiedListResponse.toDomain())
        }
    }


}
