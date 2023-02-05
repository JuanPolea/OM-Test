package com.jfmr.omtest.data.repository.unifiedList

import com.jfmr.domain.model.rtv1.RTV1Domain
import com.jfmr.domain.repository.unifiedList.UnifiedListRepository
import com.jfmr.omtest.data.model.rtv1.RTV1Response
import com.jfmr.omtest.data.model.extensions.RTV1Extensions.toDomain
import com.jfmr.omtest.data.remote.unifiedList.UnifiedListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnifiedListRepositoryImpl @Inject constructor(
    private val unifiedListRemoteDataSource: UnifiedListRemoteDataSource,
) : UnifiedListRepository {

    override suspend fun retrieveUnifiedList(): Flow<RTV1Domain> = flow {
        val response = unifiedListRemoteDataSource.retrieveList()
        if (response.isSuccessful) {
            val rtv1Response = response.body() as RTV1Response
            emit(rtv1Response.toDomain())
        }
    }
}
